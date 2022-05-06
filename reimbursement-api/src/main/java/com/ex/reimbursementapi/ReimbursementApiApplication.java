package com.ex.reimbursementapi;

import com.ex.reimbursementapi.entities.Employee;
import com.ex.reimbursementapi.entities.Reimbursement;
import com.ex.reimbursementapi.repositories.EmployeeRepository;
import com.ex.reimbursementapi.repositories.ReimbursementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReimbursementApiApplication {

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	ReimbursementRepository reimbursementRepository;


	public static void main(String[] args) {
		SpringApplication.run(ReimbursementApiApplication.class, args);
	}

/*
	@Bean
	CommandLineRunner runner (){
		return args -> {
				for(Employee employee : employeeRepository.findAll()) {
					System.out.println(employee);
				}};
	}
*/



	@Bean
	CommandLineRunner runner (){
		return args -> {
			for(Reimbursement reimbursement : reimbursementRepository.findAll()) {
				System.out.println(reimbursement);
			}};
	}

}
