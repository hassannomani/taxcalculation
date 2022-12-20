package com.eu.taxcalculation.payment.controller;

import com.eu.taxcalculation.payment.entity.Payment;
import com.eu.taxcalculation.payment.service.PaymentService;
import com.eu.taxcalculation.user.config.JwtGeneratorInterface;
import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController( PaymentService paymentService){

        this.paymentService=paymentService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePayment(@RequestBody Payment payment){
        try{
            paymentService.savePayment(payment);
            return new ResponseEntity<>(payment, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/payment-id/{id}")
    public ResponseEntity<?> getPayment(@PathVariable String id){
        try{
            Payment p = paymentService.getPaymentByUUID(id);
            return new ResponseEntity<>(p, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/payment-tin/{tin}")
    public ResponseEntity<?> getPaymentByTin(@PathVariable String tin){
        try{
            Payment p = paymentService.getPaymentByTinNo(tin);
            return new ResponseEntity<>(p, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
