package com.ex.reimbursementapi.entities;

import lombok.*;
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ReimbursementDTO {

    private String employeeName;
    private double amount;
    private String item;
    private int employeeId;

}
