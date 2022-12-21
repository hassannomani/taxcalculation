package com.eu.taxcalculation.user.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "activations")
public class Activation {
    @Id
    String uuid;
    @NonNull
    @Column(nullable = false)
    String status;

    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier  default newid()")
    private String activationCode;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date created_at;

    @Column(name = "created_at")
    @UpdateTimestamp
    private Date updated_at;

    public Activation(String uuid, String status){
        this.uuid = uuid;
        this.status = status;
    }

}
