package com.ex.reimbursementapi.repositories;


import com.ex.reimbursementapi.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository <Employee, Integer> {


    @Override
    List<Employee> findAll();


    Employee getEmployeeById(@Param("id") Integer integer);

    List<Employee> findByPosition(@Param("position") String position);

    Employee findByEmail(@Param("email") String email);
}
