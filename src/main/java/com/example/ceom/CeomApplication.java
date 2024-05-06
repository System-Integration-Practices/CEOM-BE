package com.example.ceom;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class CeomApplication {
	public static void main(String[] args) {
		SpringApplication.run(CeomApplication.class, args);
	}
}
