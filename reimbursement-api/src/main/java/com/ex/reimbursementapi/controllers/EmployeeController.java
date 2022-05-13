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
@RequestMapping("employee")

public class EmployeeController {

    final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    ReimbursementServices reimbursementServices;

    @Setter(onMethod = @__({@Autowired}))
    private ReimbursementRepository reimbursementRepository;

    @Setter(onMethod = @__({@Autowired}))
    private EmployeeRepository employeeRepository;

    /**
     * @param id int passed into url
     * @return  returns an individual reimbursement request by its unique id
     */
    @GetMapping(path="{id}")
    public ResponseEntity getMyReimbursementsById(@PathVariable int id){
        return ResponseEntity.ok(reimbursementRepository.getAllById(id));
    }


    /**
     * @param user a user query which can be set to any existing user name
     * @return //filters all reimbursement requests belonging to an employee by their name
     */
    @GetMapping("view") // employee/view?user=kevin
    public ResponseEntity getMyReimbursements(@RequestParam String user){
        return ResponseEntity.ok(reimbursementRepository.getAllByEmployeeName(user));
    }



    /**
     * @param newSubmission A DTO consisting only necessary info needed to create and submit a new request
     * @return a 200 ok status if all goes well
     * Also sends a request to the email api to send an email notifying the user
     */
    @PostMapping(path = "submit")
    public ResponseEntity submitReimbursement(@RequestBody ReimbursementDTO newSubmission){
        reimbursementServices.submitReimbursementRequest(newSubmission);
        return ResponseEntity.ok().build();
    }
}
