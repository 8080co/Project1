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

    private EmailRequester emailRequester;

    public ReimbursementServices(EmployeeRepository employeeRepository, ReimbursementRepository reimbursementRepository) {

        this.employeeRepository = employeeRepository;
        this.reimbursementRepository = reimbursementRepository;
    }
    //for  all employees

    /**
     * This method sets status to 'pending' by default, created the new reimbursement request and saves it
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
        System.out.println(employee);
        System.out.println(reimbursement);
        reimbursementRepository.save(reimbursement);
        emailRequester = new EmailRequester();
        emailRequester.sendRequest(employee.getEmail(),"Request recieved","Your request for reimbursement has been recieved.");
        return reimbursement;

    }



    //for managers only

    /**
     * This method returns a string signifying the status of the application
     * This method runs till all requests in server are dealt with
     * The decision factor was arbitrary. In this case , if the amount is above 1000, request will be declined
     * @return either 'Approved' or 'Declined' or 'All requests have been processed'
     */
   public String reviewReimbursements(){
        List <Reimbursement> pendingRequests= reimbursementRepository.findAll();
        for (Reimbursement request:pendingRequests) {
            String name = request.getEmployeeName();
            String amount = String.valueOf(request.getAmount());
            String item = request.getItem();
            if (request.getStatus().equals("Pending") && request.getAmount() >= 1000) {
                request.setStatus("Denied");
                emailRequester = new EmailRequester();
                emailRequester.sendRequest(request.getEmployee().getEmail(),"Request Denied","Your $" +amount+" reimbursement request for "+ item+ "has been denied. Company reimbursement limit reached for the year");
                return name+"'s $"+amount+" reimbursement request for "+ item+ "has been denied. Company reimbursement limit reached for the year";

            }
            else if(request.getStatus().equals("Pending")) {
                request.setStatus("Approved");
                emailRequester.sendRequest(request.getEmployee().getEmail(),"Request Approved","Your $" +amount+" reimbursement request for "+ item+ "has been approved. Payment should be processed in 2 to 5 business days");
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




