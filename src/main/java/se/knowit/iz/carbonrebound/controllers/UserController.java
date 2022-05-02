package se.knowit.iz.carbonrebound.controllers;

import org.springframework.web.bind.annotation.*;
import se.knowit.iz.carbonrebound.dto.UserDTO;
import se.knowit.iz.carbonrebound.exceptions.NotFoundUserException;
import se.knowit.iz.carbonrebound.services.UserService;

@RestController
@RequestMapping("api/auth/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/getuser")
    public UserDTO getUserById(@RequestParam Long userId) throws NotFoundUserException {
        return userService.getUserById(userId);
    }
}
