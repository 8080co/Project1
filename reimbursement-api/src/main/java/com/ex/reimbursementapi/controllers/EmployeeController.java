package com.ex.reimbursementapi.controllers;

import com.ex.reimbursementapi.entities.Reimbursement;
import com.ex.reimbursementapi.entities.ReimbursementDTO;
import com.ex.reimbursementapi.repositories.EmployeeRepository;
import com.ex.reimbursementapi.repositories.ReimbursementRepository;
import com.ex.reimbursementapi.services.ReimbursementServices;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    ReimbursementServices reimbursementServices;

    @Setter(onMethod = @__({@Autowired}))
    private ReimbursementRepository reimbursementRepository;

    @Setter(onMethod = @__({@Autowired}))
    private EmployeeRepository employeeRepository;

    //returns an individual reimbursement request by its unique id
    @GetMapping(path="{id}")
    public ResponseEntity getMyReimbursementsById(@PathVariable int id){
        return ResponseEntity.ok(reimbursementRepository.getAllById(id));
    }
    //filters all reimbursement requests belonging to an employee by their name
    @GetMapping("view") // employee/view?user=kevin
    public ResponseEntity getMyReimbursements(@RequestParam String user){
        return ResponseEntity.ok(reimbursementRepository.getAllByEmployeeName(user));
    }


    //submit a new reimbursement
    @PostMapping(path = "submit")
    public ResponseEntity submitReimbursement(@RequestBody ReimbursementDTO newSubmission){
        reimbursementServices.submitReimbursementRequest(newSubmission);
        return ResponseEntity.ok().build();
    }
}
