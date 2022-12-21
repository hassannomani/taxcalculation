package com.eu.taxcalculation.user.service;

import com.eu.taxcalculation.user.entity.Activation;
import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.repository.ActivationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ActivationService {
    ActivationRepository activationRepository;
    @Autowired
    public ActivationService(ActivationRepository activationRepository){
        this.activationRepository= activationRepository;
    }

    public void saveActivation(User user) {
        //String password = user.getPassword();
        Activation activation = new Activation(user.getUuid(),"inctive");
        activationRepository.save(activation);
    }

    public Activation activateUser(String id) throws Exception {
        Activation activation = activationRepository.findByUuid(id);
        if(activation!=null)
            throw new Exception("code_not_found");
        else{
            activation.setStatus("active");
            activationRepository.save(activation);
            return activation;

        }

    }
}
