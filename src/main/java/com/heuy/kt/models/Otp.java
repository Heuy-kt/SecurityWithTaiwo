package com.heuy.kt.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "otp")
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Date expiryDate;

    public Otp(){
        this.expiryDate = new Date(System.currentTimeMillis()+ 10*60*1000);
    }

}
