package com.ex.reimbursementapi.services;

import com.ex.reimbursementapi.entities.Employee;
import com.ex.reimbursementapi.entities.Reimbursement;
import com.ex.reimbursementapi.entities.ReimbursementDTO;
import com.ex.reimbursementapi.exceptions.CannotSendEmailWithoutReviewException;
import com.ex.reimbursementapi.repositories.EmployeeRepository;
import com.ex.reimbursementapi.repositories.ReimbursementRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReimbursementServices {

    private EmployeeRepository employeeRepository;
    private ReimbursementRepository reimbursementRepository;

    public ReimbursementServices(EmployeeRepository employeeRepository, ReimbursementRepository reimbursementRepository) {

        this.employeeRepository = employeeRepository;
        this.reimbursementRepository = reimbursementRepository;
    }
    //for  all employees

    /**
     * This method sets status to 'pending' by default, created the new reimbursment request and saves it
     * @param newRequest A DTO that only takes name, amount , item and employeeId
     * @return the new reimbursement request that was just created
     */
    public Reimbursement submitReimbursementRequest(ReimbursementDTO newRequest){
        Reimbursement reimbursement = new Reimbursement();

        reimbursement.setEmployeeName(newRequest.getEmployeeName());
        reimbursement.setAmount(newRequest.getAmount());
        reimbursement.setItem(newRequest.getItem());

        int employeeId= newRequest.getEmployeeId();
        Employee employee = employeeRepository.getEmployeeById(employeeId);
        reimbursement.setEmployee(employee);
        reimbursement.setStatus("Pending");

        reimbursementRepository.save(reimbursement);

        return reimbursement;

    }



    //for managers only

    /**
     * This method returns a status a string signifying the status of the application
     * This method runs till all requests in server are dealt with
     * The decision factor was arbitrary. In this case , if the amount is above 1000, request will be declined
     * @return either 'Approved' or 'Declined' or 'All requests have been processed'
     */
   public String reviewReimbursements(){
        List <Reimbursement> pendingRequests= reimbursementRepository.findAll();
        for (Reimbursement request:pendingRequests) {
            String name = request.getEmployeeName();
            String amount = String.valueOf(request.getAmount());
            if (request.getStatus().equals("Pending") && request.getAmount() >= 1000) {
                request.setStatus("Denied");
                return name+"'s $"+amount+" reimbursement request declined. Company reimbursement limit reached for the year";
            }
            else if(request.getStatus().equals("Pending")) {
                request.setStatus("Approved");
                return "Approved";
            }
        }
        return "All requests have been processed";


    }

//    public int requestEmailApi(ReimbursementDTO reviewedReimbursements) {
//        Reimbursement reimbursement = new Reimbursement();
//
//        if (reviewedReimbursements.getStatus().equals("Approved") || reviewedReimbursements.getStatus().equals("Denied"))
//
//            return reviewedReimbursements.getId();
//        return 0;
//    }

    }




