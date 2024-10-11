package com.heuy.kt.security.web;

import com.heuy.kt.models.Otp;
import com.heuy.kt.repo.OTPRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OTPService {

    private final JavaMailSender jms;
    private final OTPRepo otpRepo;
    @Value("${spring.mail.username}") private String sender;

    public void sendOTP(Otp otp){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(otp.getEmail());
            message.setText("from HEUY: your otp code is: " + otp);
            message.setSubject("OTP");
            jms.send(message);
            otpRepo.save(otp);
        }catch(Exception e) {
            e.getMessage();
        }

    }

}
