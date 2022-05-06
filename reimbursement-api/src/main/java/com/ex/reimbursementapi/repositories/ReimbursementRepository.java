package com.ex.reimbursementapi.repositories;

import com.ex.reimbursementapi.entities.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimbursementRepository  extends JpaRepository<Reimbursement, Integer> {


    //manager can use to get all reimbursement requests
    @Override
    List<Reimbursement> findAll();

    //employee can use to get one of their current requests by id
    List<Reimbursement> getAllById( @Param("employee_id") int id);

    List<Reimbursement> getAllByEmployeeName(@Param("employee_name")String employeeName);


    //find by status of request (Approved,Denied,Pending(default))
    Reimbursement findByStatus(@Param("_status")String status);

}
