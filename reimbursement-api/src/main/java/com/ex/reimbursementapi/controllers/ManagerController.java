package com.ex.reimbursementapi.controllers;

import com.ex.reimbursementapi.entities.Reimbursement;
import com.ex.reimbursementapi.entities.ReimbursementDTO;
import com.ex.reimbursementapi.repositories.EmployeeRepository;
import com.ex.reimbursementapi.repositories.ReimbursementRepository;
import com.ex.reimbursementapi.services.ReimbursementServices;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("manager")
public class ManagerController {

    final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    ReimbursementServices reimbursementServices;

    @Setter(onMethod = @__({@Autowired}))
    private ReimbursementRepository reimbursementRepository;

    @Setter(onMethod = @__({@Autowired}))
    private EmployeeRepository employeeRepository;


    // View all requests in system
    @GetMapping(path="view-all-requests")
    public ResponseEntity getAllReimbursements(){
        return ResponseEntity.ok(reimbursementRepository.findAll());
    }

    /**
     * @return - a string signifying status of sent request
     * Also sends the email api a request to send an email to the user regarding the status of their  reviewed request
     */
    @PutMapping(path="review-requests")
    public String reviewRequests(){
        return reimbursementServices.reviewReimbursements();
    }


}


