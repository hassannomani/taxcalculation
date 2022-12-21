package com.eu.taxcalculation.user.controller;

import com.eu.taxcalculation.user.config.JwtGeneratorInterface;
import com.eu.taxcalculation.user.entity.Activation;
import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.exception.UserNotFoundException;
import com.eu.taxcalculation.user.service.ActivationService;
import com.eu.taxcalculation.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtGeneratorInterface jwtGenerator;

    @Autowired
    private ActivationService activationService;

    @Autowired
    public UserController(UserService userService, ActivationService activationService, JwtGeneratorInterface jwtGenerator){
        this.userService=userService;
        this.jwtGenerator=jwtGenerator;
        this.activationService = activationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody User user){
        try{
            User user1 = userService.saveUser(user);
            activationService.saveActivation(user1);
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            if(user.getUsername() == null || user.getPassword() == null) {
                throw new UserNotFoundException("Username or Password is Empty");
            }
            User userData = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
            if(userData == null){
                throw new UserNotFoundException("Username or Password is Invalid");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/all")

    public ResponseEntity<?> GetAllUsers() {
        return new ResponseEntity<>(userService.getAllTaxPayer(), HttpStatus.OK);
    }

    @GetMapping("/activation/{code}")
    public ResponseEntity<?> activateUser(@PathVariable String code){
        try{
            Activation activation = activationService.activateUser(code);
            //activationService.saveActivation(user1);
            return new ResponseEntity<>(activation, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
