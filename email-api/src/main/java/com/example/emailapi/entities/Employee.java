package com.example.emailapi.entities;
import lombok.*;

import javax.persistence.*;
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @Column(name = "id", columnDefinition = "AUTO_INCREMENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "_name", unique = true)
    private String name;

    // position is set 1 of 2 options( employee or manager)
    @Column(name = "position")
    private String position;

    @Column(name = "email" ,unique = true, nullable = false)
    private String email;
}