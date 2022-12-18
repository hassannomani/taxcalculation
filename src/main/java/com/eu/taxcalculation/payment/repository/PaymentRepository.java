package com.eu.taxcalculation.payment.repository;

import com.eu.taxcalculation.payment.entity.Payment;
import com.eu.taxcalculation.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PaymentRepository extends JpaRepository<User, Long> {
    public Payment findById(String id);

    public Payment savePayment(Payment payment);

    public Payment findByUserId(String userid);
}
