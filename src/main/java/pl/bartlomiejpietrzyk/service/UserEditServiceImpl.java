package pl.bartlomiejpietrzyk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartlomiejpietrzyk.dto.UserEditDto;
import pl.bartlomiejpietrzyk.entity.Address;
import pl.bartlomiejpietrzyk.entity.User;
import pl.bartlomiejpietrzyk.repository.AddressRepository;
import pl.bartlomiejpietrzyk.repository.UserRepository;

@Service
@Transactional
public class UserEditServiceImpl implements UserEditService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public UserEditServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public void userDataEdit(UserEditDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        Address address = user.getAddress();
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setMobile(userDto.getMobile());
        user.setEmail(userDto.getEmail());
        address.setBuilding(Integer.valueOf(userDto.getBuilding()));
        address.setCity(userDto.getCity());
        address.setHomeNumber(Integer.valueOf(userDto.getHome()));
        address.setStreet(userDto.getStreet());
        address.setPostCode(userDto.getPostCode());
        user.setAddress(address);
        userRepository.save(user);
    }
}

