package com.eu.taxcalculation.user.service;

import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.exception.UserNotFoundException;
import com.eu.taxcalculation.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplemented implements UserService{
    private UserRepository userRepository;

    @Autowired
    public void UserServiceImplemented(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) throws UserNotFoundException {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if(user == null){
            throw new UserNotFoundException("Invalid id and password");
        }
        return user;
    }
}
