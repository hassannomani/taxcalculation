package com.eu.taxcalculation.user.service;

import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.exception.UserNotFoundException;
import com.eu.taxcalculation.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImplemented implements UserService{
    private UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public void UserServiceImplemented(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public void saveUser(User user) {
        //String password = user.getPassword();
        this.passwordEncoder = new BCryptPasswordEncoder();
        String pass = this.passwordEncoder.encode(user.getPassword());
        System.out.println("The pass is "+pass);
        user.setPassword(pass);
        userRepository.save(user);

    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) throws UserNotFoundException {
        this.passwordEncoder = new BCryptPasswordEncoder();

        //User user = userRepository.findByUsernameAndPassword(username, pass);
        User user = userRepository.findByUsername(username);
        boolean isPasswordMatches = this.passwordEncoder.matches(password, user.getPassword());

        if(user == null){
            throw new UserNotFoundException("Invalid id and password");
        }
        return user;
    }
}
