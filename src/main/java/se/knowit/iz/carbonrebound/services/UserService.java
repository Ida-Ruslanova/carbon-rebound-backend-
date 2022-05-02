package se.knowit.iz.carbonrebound.services;

import org.springframework.stereotype.Service;
import se.knowit.iz.carbonrebound.dto.UserDTO;
import se.knowit.iz.carbonrebound.entities.User;
import se.knowit.iz.carbonrebound.exceptions.NotFoundUserException;
import se.knowit.iz.carbonrebound.exceptions.NotFoundVehicleException;
import se.knowit.iz.carbonrebound.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserById(Long userId) throws NotFoundUserException {
        Optional< UserDTO > userDTO = userRepository.findById(userId).map(this::userEntityToDto);
        if (userDTO.isPresent()) {
            return userDTO.get();
        } else {
            throw new NotFoundUserException();
        }
    }

    public UserDTO userEntityToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstname());
        userDTO.setLastName(user.getLastname());
        userDTO.setUserName(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
