package com.ex.reimbursementapi.controllers;

import com.ex.reimbursementapi.entities.Reimbursement;
import com.ex.reimbursementapi.repositories.EmployeeRepository;
import com.ex.reimbursementapi.repositories.ReimbursementRepository;
import com.ex.reimbursementapi.services.ReimbursementServices;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    ReimbursementServices reimbursementServices;

    @Setter(onMethod = @__({@Autowired}))
    private ReimbursementRepository reimbursementRepository;

    @Setter(onMethod = @__({@Autowired}))
    private EmployeeRepository employeeRepository;

    @GetMapping(path="view-all-requests")
    public ResponseEntity getAllReimbursements(){
        return ResponseEntity.ok(reimbursementRepository.findAll());
    }

    @PutMapping(path="review-requests")
    public String reviewRequests(){
        return reimbursementServices.reviewReimbursements();
//        return ResponseEntity.ok().build().toString();
    }

//    @PostMapping(path="request-email")
//    public Reimbursement requestEmailApi (){
//        return reimbursementServices.requestEmailApi();
//    }
}


