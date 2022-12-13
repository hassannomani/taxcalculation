package com.eu.taxcalculation.user.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true, updatable = false, nullable = false)
    private String uuid;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String username;

    @NonNull
    @Column(nullable = false)
    private String password;


    @NonNull
    @Column(nullable = false)
    private String  tin;

    @NonNull
    @Column(nullable = false)
    private String roles;

    @Column(columnDefinition = "tinyint default 1")
    private Boolean enabled;

    private Date dob;

    public User(String id, String username, String password, String roles, String tin, Date dob, Boolean enabled) {
        this.uuid = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.tin = tin;
        this.dob = dob;
    }
}
