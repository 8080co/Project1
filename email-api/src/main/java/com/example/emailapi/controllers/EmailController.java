package com.example.emailapi.controllers;

import com.example.emailapi.entities.EmailRequest;
import com.example.emailapi.services.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private EmailSender emailSender;

    @PostMapping()
    public ResponseEntity sendEmail(@RequestBody EmailRequest emailRequest){
        try{
            emailSender.sendEmail(emailRequest.getToEmail(),emailRequest.getSubject(),emailRequest.getBody());
            return ResponseEntity.accepted().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
}
