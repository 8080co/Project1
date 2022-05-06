package com.example.emailapi.repositories;

import com.example.emailapi.entities.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimbursementRepository  extends JpaRepository<Reimbursement, Integer> {


    // can be used to get one of the current requests by id
    Reimbursement getById(@Param("employee_id") int id);
}


