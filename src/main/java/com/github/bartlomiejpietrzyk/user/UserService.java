package com.github.bartlomiejpietrzyk.user;

import com.github.bartlomiejpietrzyk.panel.admin.UserEditDto;
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

    public List<UserDto> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    public UserDto findUserById(String id) {
        return new UserDto(userRepository.findUserById(Long.valueOf(id)));
    }

    public User update(UserEditDto userDto) {
        return userRepository.saveAndFlush(userDto);

    }

    public void deleteUser(String id) {
        userRepository.delete(Long.valueOf(id));
    }
}
