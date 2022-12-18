package com.eu.taxcalculation.payment.controller;

import com.eu.taxcalculation.payment.service.PaymentService;
import com.eu.taxcalculation.user.config.JwtGeneratorInterface;
import com.eu.taxcalculation.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private UserService userService;
    private PaymentService paymentService;

    @Autowired
    public PaymentController(UserService userService, PaymentService paymentService){
        this.userService=userService;
        this.paymentService=paymentService;
    }

}
