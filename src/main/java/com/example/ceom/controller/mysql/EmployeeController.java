package com.example.ceom.controller.mysql;


import com.example.ceom.model.mysql.Employee;
import com.example.ceom.response.EmployeeListResponse;
import com.example.ceom.response.EmployeeResponse;
import com.example.ceom.response.PersonalBirthdayResponse;
import com.example.ceom.service.mysql.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeMySqlService;

    @GetMapping("")
    public ResponseEntity<?> getPersonalsForAlert(
            @RequestParam(name = "fullName", defaultValue = "") String fullName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        PageRequest pageRequest = PageRequest.of(
                page, limit
                //Sort.by("createdAt").descending()
//                Sort.by("First Name").ascending()
        );
        try {
            Page<Employee> employeePage = employeeMySqlService.getEmployeesByFullNameOrPayRate(fullName, pageRequest);
            Page<EmployeeResponse> list = employeePage.map(EmployeeResponse::fromEmployee);
            List<EmployeeResponse> result = list.getContent();

            return ResponseEntity.ok(EmployeeListResponse.builder()
                    .employees(result)
                    .totalPages(list.getTotalPages())
                    .currentPage(list.getNumber())
                    .itemPerPage(list.getNumberOfElements())
                    .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
