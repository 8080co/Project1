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
@Table(name = "Reimbursement")
public class Reimbursement {


    @Id
    @Column(name = "id", columnDefinition = "AUTO_INCREMENT")
    private int id;         //will be primary key. will be auto-generated

    @Column(name = "employee_name")
    private String employeeName;    //name of employee

    @Column(name = "amount")
    private double amount; //amount to be reimbursed

    @Column(name = "item")
    private String item;   //type of request i.e , training, health, courses etc

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;  //employee id (foreign key) references id from employee pojo


    @Column(name = "_status")
    private String status; // status set by manager after review i.e, "approved","denied","pending"


}

