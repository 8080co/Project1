package com.ex.reimbursementapi;

import com.ex.reimbursementapi.entities.Employee;
import com.ex.reimbursementapi.entities.Reimbursement;
import com.ex.reimbursementapi.entities.ReimbursementDTO;
import com.ex.reimbursementapi.repositories.EmployeeRepository;
import com.ex.reimbursementapi.repositories.ReimbursementRepository;
import com.ex.reimbursementapi.services.ReimbursementServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReimbursementServicesTests {

    @Autowired
    private ReimbursementRepository reimbursementRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ReimbursementServices reimbursementServices;

    private Employee employee;
    private Reimbursement reimbursement;
    private ReimbursementDTO reimbursementDTO;

    @BeforeEach
    public void initTest() {
        employee = Employee.builder().id(1).name("Kevin").position("employee").email("kevin@gmail.com").build();

        reimbursement = Reimbursement.builder().id(1).employeeName("Kevin").amount(100).item("travel").employee(employee).status("Pending").build();

        reimbursementDTO = ReimbursementDTO.builder().employeeName("Smith").amount(500).item("medical").employeeId(2).build();

        reimbursementRepository.save(reimbursement);
    }

    @Test
    public void shouldFindEmployeeById(){
        Employee employee = employeeRepository.getEmployeeById(1);
        Assertions.assertEquals("Kevin", employee.getName());
        Assertions.assertEquals("kevin@gmail.com",employee.getEmail());
    }
    @Test
    public void shouldFindEmployeeByEmail(){
        Employee employee = employeeRepository.findByEmail("kevin@gmail.com");
        Assertions.assertEquals("Kevin",employee.getName());
    }
    @Test
    public void  shouldFindEmployeeByPosition(){
        List<Employee> employee = employeeRepository.findByPosition("employee");
        Assertions.assertEquals("Kevin",employee.get(0).getName());
    }

//    @Test
//    public void shouldSubmitReimbursementRequest(){
//        Reimbursement newReimbursement = reimbursementServices.submitReimbursementRequest(reimbursementDTO);
//        Assertions.assertEquals("Smith", newReimbursement.getEmployeeName());
//        Assertions.assertEquals(500, newReimbursement.getAmount());
//        Assertions.assertEquals("medical", newReimbursement.getItem());
//
//    }




    @Test
    public void shouldFindAllReimbursements(){
        List<Reimbursement> reimbursements = reimbursementRepository.findAll();
        Assertions.assertEquals("Kevin",reimbursements.get(0).getEmployeeName());
    }

    @Test
    public void shouldFindAllReimbursementsByUsername(){
        List<Reimbursement> reimbursements = reimbursementRepository.getAllByEmployeeName("Kevin");
        Assertions.assertEquals("travel",reimbursements.get(0).getItem());

    }
    @Test
    public void shouldFindReimbursementById(){
        List<Reimbursement> reimbursements = reimbursementRepository.getAllById(1);
        Assertions.assertEquals(100,reimbursements.get(0).getAmount());

    }
}
