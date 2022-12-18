package com.eu.taxcalculation.payment.entity;

import com.eu.taxcalculation.user.entity.User;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default newid()")
    private String uuid;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid" , insertable = false,updatable = false)
    private User userid;

    @NonNull
    @Column(nullable = false)
    private double amount;



    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    public String getUuid() {
        return uuid;
    }


    public double getAmount() {
        return amount;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public Payment(){}
    public Payment(String uuid, User userid, double amount) {
        this.uuid = uuid;
        this.userid = userid;
        this.amount = amount;
    }
}
