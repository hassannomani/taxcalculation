package com.eu.taxcalculation.payment.service;

import com.eu.taxcalculation.payment.entity.Payment;
import com.eu.taxcalculation.payment.exception.PaymentNotFoundException;
import com.eu.taxcalculation.payment.repository.PaymentRepository;
import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.exception.UserNotFoundException;
import com.eu.taxcalculation.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class PaymentServiceImp implements PaymentService{
    private UserRepository userRepository;
    private PaymentRepository paymentRepository;

    @Autowired
    public void PaymentServiceImp(UserRepository userRepository, PaymentRepository paymentRepository){
        this.userRepository=userRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void savePayment(Payment payment) {
        paymentRepository.savePayment(payment);
    }

    @Override
    public Payment getPaymentById(String id) throws PaymentNotFoundException {

        Payment payment = paymentRepository.findById(id);

        if(payment == null){
            throw new PaymentNotFoundException("Invalid payment id");
        }
        return payment;
    }

    @Override
    public Payment getPaymentByUserId(String id) throws PaymentNotFoundException {

        Payment payment = paymentRepository.findByUserId(id);

        if(payment == null){
            throw new PaymentNotFoundException("Invalid payment id");
        }
        return payment;
    }

}
