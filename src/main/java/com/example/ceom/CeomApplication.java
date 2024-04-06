package com.example.ceom;

import com.example.ceom.dto.MergeDto;
import com.example.ceom.model.mysql.nhanvien;
import com.example.ceom.model.sqlserver.NHANVIEN;
import com.example.ceom.repository.mysql.nhanvienRepository;
import com.example.ceom.repository.mysql.phongbanRepository;
import com.example.ceom.repository.sqlserver.NHANVIENRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class CeomApplication {

	//    private final EmployeeRepository employeeRepository;
	private final com.example.ceom.repository.mysql.nhanvienRepository  nhanvienRepository;
	private final com.example.ceom.repository.mysql.phongbanRepository  phongbanRepository;
	private final com.example.ceom.repository.sqlserver.NHANVIENRepository  NHANVIENRepository;

	public static void main(String[] args) {
		SpringApplication.run(CeomApplication.class, args);
	}

	@Bean
	public ApplicationRunner init() {
		return args -> {
			List<NHANVIEN> NHANVIENList = NHANVIENRepository.fetchAll();
			List<nhanvien> nhanvienList = nhanvienRepository.fetchAll();
			List<MergeDto> mergeDtoList = NHANVIENList.stream()
					.flatMap(
							table1 -> nhanvienList.stream()
									.filter(table2-> table1.getMANHANVIEN() == table2.getMANHANVIEN())
									.map(table2 -> new MergeDto(table2.getMANHANVIEN(),
											table2.getHO(),
											table2.getTEN(),
											table2.getPhongban().getTENPHONGBAN(),
											table1.getLuongs().get(0).getLUONGCOBAN())))
					.toList();
			mergeDtoList.forEach(System.out::println);

		};
	}

}
