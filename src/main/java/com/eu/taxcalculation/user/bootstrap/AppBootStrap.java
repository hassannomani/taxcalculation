package com.eu.taxcalculation.user.bootstrap;

import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

@Component
public class AppBootStrap implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public AppBootStrap(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<User> user1 = Optional.ofNullable(userRepository.findByUsernameAndPassword("user", "password"));
        if (user1.isEmpty()){
            User userX = new User(UUID.randomUUID().toString(), "user", "1234", "ROLE_USER", "111111111111" , Date.valueOf("1970-01-01"),true);
            userRepository.save(userX);
        }

        Optional<User> user2 = Optional.ofNullable(userRepository.findByUsernameAndPassword("user", "password"));
        if (user1.isEmpty()){
            User userY = new User(UUID.randomUUID().toString(), "admin", "admin", "ROLE_ADMIN", "000000000000" , Date.valueOf("1970-01-01"),true);
            userRepository.save(userY);
        }
    }
}
