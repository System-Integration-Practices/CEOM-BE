package com.example.ceom.controller;

import com.example.ceom.service.ExcelImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/import")
@CrossOrigin(origins = "*")
public class ExcelImportController {

    @Autowired
    private ExcelImportService excelImportService;

    @PostMapping("/employee")
    public ResponseEntity<String> importDataEmployee(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            excelImportService.importEmployees(file.getInputStream());
            return ResponseEntity.ok("Data imported successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error importing data: " + e.getMessage());
        }
    }

    @PostMapping("/employment")
    public ResponseEntity<String> importDataEmployment(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            excelImportService.importEmployment(file.getInputStream());
            return ResponseEntity.ok("Data imported successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error importing data: " + e.getMessage());
        }
    }

    @PostMapping("/personal")
    public ResponseEntity<String> importDataPersonal(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            excelImportService.importPersonal(file.getInputStream());
            return ResponseEntity.ok("Data imported successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error importing data: " + e.getMessage());
        }
    }
}
