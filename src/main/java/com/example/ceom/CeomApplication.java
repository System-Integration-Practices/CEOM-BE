package com.example.ceom;

import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.repository.mysql.PayRateRepository;
import com.example.ceom.repository.sqlserver.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CeomApplication {

	private final PersonRepository personRepository;

	@Autowired
	public CeomApplication(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CeomApplication.class, args);
	}

	@Bean
	public ApplicationRunner init() {
		return args -> {
			personRepository.findAll().forEach(System.out::println);
		};
	}
}
