package se.knowit.iz.carbonrebound.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class UserController {

}
