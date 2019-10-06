package pl.bartlomiejpietrzyk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bartlomiejpietrzyk.account.LostPasswordService;
import pl.bartlomiejpietrzyk.dto.UserLostPasswordDto;
import pl.bartlomiejpietrzyk.dto.UserResetPasswordDto;
import pl.bartlomiejpietrzyk.email.EmailService;
import pl.bartlomiejpietrzyk.entity.PasswordToken;
import pl.bartlomiejpietrzyk.entity.User;
import pl.bartlomiejpietrzyk.repository.PasswordTokenRepository;
import pl.bartlomiejpietrzyk.repository.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping
public class UserLostPasswordController {
    private final UserRepository userRepository;
    private final PasswordTokenRepository passwordTokenRepository;
    private final LostPasswordService passwordService;
    private final EmailService emailService;

    @Autowired
    public UserLostPasswordController(UserRepository userRepository,
                                      PasswordTokenRepository passwordTokenRepository, LostPasswordService passwordService,
                                      EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordTokenRepository = passwordTokenRepository;
        this.passwordService = passwordService;
        this.emailService = emailService;
    }

    @GetMapping("/password/lost")
    public String lostPasswordHome(Model model) {
        model.addAttribute("user", new UserLostPasswordDto());
        return "lostPassword";
    }

    @PostMapping("/password/lost")
    public String lostPasswordProceed(@ModelAttribute("user") @Valid UserLostPasswordDto userDto,
                                      BindingResult result) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (result.hasErrors()) {
            return "redirect:/lostPassword?error";
        }
        if (user == null) {
            return "redirect:/lostPassword?notExist";
        }
        if (!user.getEnabled()) {
            return "redirect:/lostPassword?disabled";
        }
        PasswordToken passwordToken = passwordService.createPasswordToken(user);
        SimpleMailMessage simpleMailMessage = passwordService
                .constructResetTokenEmail(passwordToken.getToken(), user);
        emailService.sendMessage(user.getEmail(),
                simpleMailMessage.getSubject(), simpleMailMessage.getText());
        return "redirect:/lostPassword?sent";
    }


    @GetMapping("/password/reset")
    public String resetPasswordHome(@RequestParam Long id,
                                    @RequestParam String token, Model model) {
        PasswordToken byToken = passwordTokenRepository.findByToken(token);
        if (byToken == null) {
            return "redirect:/lostPassword?notoken";
        } else if (byToken.getId() != null && byToken.getTokenUsed() != null) {
            return "redirect:/lostPassword?usedtoken";
        }
        if (passwordService.validatePasswordToken(id, token) != null) {
            return "redirect:/lostPassword?error";
        }
        UserResetPasswordDto userChangeLostPasswordDto =
                new UserResetPasswordDto(userRepository.getOne(id));
        userChangeLostPasswordDto.setToken(token);
        model.addAttribute("reset", userChangeLostPasswordDto);

        return "resetPassword";
    }
}
