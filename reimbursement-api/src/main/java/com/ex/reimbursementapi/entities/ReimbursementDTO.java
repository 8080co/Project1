package com.ex.reimbursementapi.entities;

import lombok.*;
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

// This was created so that a response body with only necessary info needed to be sent to create a request
public class ReimbursementDTO {

    private String employeeName;
    private double amount;
    private String item;
    private int employeeId;

}
