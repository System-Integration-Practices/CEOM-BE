package com.example.ceom;

import com.example.ceom.model.mysql.Employee;
import com.example.ceom.model.sqlserver.Personal;
import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.repository.mysql.PayRateRepository;
import com.example.ceom.repository.sqlserver.*;
import com.example.ceom.service.mysql.impl.PayRateServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class CeomApplication {
	/**
	 * MySQL
	 */
	private final EmployeeRepository employeeRepository;
	private final PayRateRepository payRateRepository;
	private final PayRateServiceImpl payRateService;

	/**
	 * Sql server
	 */
	private final PersonRepository personRepository;
	private final EmploymentRepository employmentRepository;
	private final BenefitPlansRepository benefitPlansRepository;
	private final EmploymentWorkingTimeRepository employmentWorkingTimeRepository;
	private final JobHistoryRepository jobHistoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(CeomApplication.class, args);
	}

	@Bean
	public ApplicationRunner init() {
		return args -> {
		};
	}

}
