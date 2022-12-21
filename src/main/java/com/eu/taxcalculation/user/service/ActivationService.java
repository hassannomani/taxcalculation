package com.eu.taxcalculation.user.service;

import com.eu.taxcalculation.user.repository.ActivationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivationService {
    ActivationRepository activationRepository;
    @Autowired
    public ActivationService(ActivationRepository activationRepository){
        this.activationRepository= activationRepository;
    }

}
