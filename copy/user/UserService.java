package pl.bartlomiejpietrzyk.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartlomiejpietrzyk.user.dto.AdminUserEditDto;
import pl.bartlomiejpietrzyk.user.dto.UserDetailsDto;
import pl.bartlomiejpietrzyk.user.dto.UserEditDto;
import pl.bartlomiejpietrzyk.user.dto.UserListDto;

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

    public AdminUserEditDto findUserById(String id) {
        return new AdminUserEditDto(userRepository.getOne(Long.valueOf(id)));
    }

    public UserDetailsDto findUserDetailsById(String id) {
        return new UserDetailsDto(userRepository.getOne(Long.valueOf(id)));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void adminEditUpdate(AdminUserEditDto adminUserEditDto) {
        User existing = userRepository.getOne(Long.valueOf(adminUserEditDto.getId()));
        userRepository.save(setAdminUserFromDtoEditForm(existing, adminUserEditDto));
    }

    public void userEditUpdate(UserEditDto userEditDto) {
        User existing = userRepository.getOne(Long.valueOf(userEditDto.getId()));
        userRepository.save(setUserFromDtoEditForm(existing, userEditDto));
    }

    public User setAdminUserFromDtoEditForm(User user, AdminUserEditDto adminUserEditDto) {
        user.setEmail(adminUserEditDto.getEmail());
        user.setFirstName(adminUserEditDto.getFirstName());
        user.setLastName(adminUserEditDto.getLastName());
        user.setMobile(Long.valueOf(adminUserEditDto.getMobile()));
        user.setAddress(adminUserEditDto.getAddress());
        user.setPostCode(adminUserEditDto.getPostCode());
        user.setCity(adminUserEditDto.getCity());
        user.setLocked(adminUserEditDto.getLocked());
        user.setRoles(adminUserEditDto.getRoles().stream().map(Role::new).collect(Collectors.toList()));
        user.setEnable(adminUserEditDto.getEnable());
        return user;
    }

    public User setUserFromDtoEditForm(User user, UserEditDto userEditDto) {
        user.setEmail(userEditDto.getEmail());
        user.setFirstName(userEditDto.getFirstName());
        user.setLastName(userEditDto.getLastName());
        user.setMobile(Long.valueOf(userEditDto.getMobile()));
        user.setAddress(userEditDto.getAddress());
        user.setPostCode(userEditDto.getPostCode());
        user.setCity(userEditDto.getCity());
        return user;
    }

    public UserEditDto findUserByEmail(String email) {
        return new UserEditDto(userRepository.findByEmail(email));
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}
