package com.github.bartlomiejpietrzyk.user;

import com.github.bartlomiejpietrzyk.panel.admin.UserEditDto;
import com.github.bartlomiejpietrzyk.panel.admin.UserListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserListDto> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(UserListDto::new)
                .collect(Collectors.toList());
    }

    public UserEditDto findUserById(String id) {
        return new UserEditDto(userRepository.getOne(Long.valueOf(id)));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void editUpdate(UserEditDto userEditDto) {
        User existing = userRepository.getOne(Long.valueOf(userEditDto.getId()));
        userRepository.save(setUserFromDtoEditForm(existing, userEditDto));
    }
    
    public User setUserFromDtoEditForm(User user, UserEditDto userEditDto) {
        user.setFirstName(userEditDto.getFirstName());
        user.setLastName(userEditDto.getLastName());
        user.setMobile(Long.valueOf(userEditDto.getMobile()));
        user.setAddress(userEditDto.getAddress());
        user.setPostCode(userEditDto.getPostCode());
        user.setCity(userEditDto.getCity());
        return user;
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}
