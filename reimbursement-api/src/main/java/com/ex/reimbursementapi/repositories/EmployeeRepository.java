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

    //get list of employees filtered by their position(employee or manager)
    List<Employee> findByPosition(@Param("position") String position);

    //get a single employee based off their email(which is unique)
    Employee findByEmail(@Param("email") String email);
}
