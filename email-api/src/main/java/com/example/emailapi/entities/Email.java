package com.example.emailapi.entities;

import lombok.*;

import javax.persistence.*;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="Emails")
public class Email {
    @Id
    @Column(name="id", columnDefinition = "AUTO_INCREMENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emailId;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="email_body")
    private String emailBody;
}
