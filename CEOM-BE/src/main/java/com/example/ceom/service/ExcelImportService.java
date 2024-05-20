package com.example.ceom.service;

import com.example.ceom.entity.mysql.Employee;
import com.example.ceom.entity.mysql.PayRate;
import com.example.ceom.entity.sqlserver.BenefitPlans;
import com.example.ceom.entity.sqlserver.Employment;
import com.example.ceom.entity.sqlserver.Personal;
import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.repository.mysql.PayRateRepository;
import com.example.ceom.repository.sqlserver.BenefitPlansRepository;
import com.example.ceom.repository.sqlserver.EmploymentRepository;
import com.example.ceom.repository.sqlserver.PersonRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.Optional;

@Service
public class ExcelImportService {

    @Autowired
    private PayRateRepository payRateRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmploymentRepository employmentRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BenefitPlansRepository benefitPlansRepository;

    @Transactional
    public void importEmployees(InputStream inputStream) {
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                // Skip header row (assuming it's the first row)
                if (row.getRowNum() == 0) {
                    continue;
                }

                Employee employee = createEmployeeFromRow(row);
                Optional<Employee> existingEmployee = Optional.ofNullable(employeeRepository.findByEmployeeNumber(employee.getEmployeeNumber()));

                if (existingEmployee.isPresent()) {
                    // Update existing employee
                    Employee existing = existingEmployee.get();
                    updateEmployee(existing, employee);
                    employeeRepository.save(existing);
                } else {
                    // Save new employee
                    employeeRepository.save(employee);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error importing employees from Excel file", e);
        }
    }

    private Employee createEmployeeFromRow(Row row) {
        Employee employee = new Employee();

        // Assuming employee number is in column 0
        Cell cell = row.getCell(0);
        if (cell != null) {
            if (cell.getCellType() == CellType.NUMERIC) {
                employee.setEmployeeNumber((int) cell.getNumericCellValue());
            } else if (cell.getCellType() == CellType.STRING) {
                employee.setEmployeeNumber(Integer.parseInt(cell.getStringCellValue()));
            }
        }

        // First Name in column 1
        cell = row.getCell(1);
        if (cell != null && cell.getCellType() == CellType.STRING) {
            employee.setFirstName(cell.getStringCellValue());
        }

        // Last Name in column 2
        cell = row.getCell(2);
        if (cell != null && cell.getCellType() == CellType.STRING) {
            employee.setLastName(cell.getStringCellValue());
        }

        // SSN in column 3
        cell = row.getCell(3);
        if (cell != null) {
            if (cell.getCellType() == CellType.NUMERIC) {
                employee.setSsn(cell.getNumericCellValue());
            } else if (cell.getCellType() == CellType.STRING) {
                employee.setSsn(Double.parseDouble(cell.getStringCellValue()));
            }
        }

        // Employee ID in column 4
        cell = row.getCell(4);
        if (cell != null) {
            if (cell.getCellType() == CellType.NUMERIC) {
                employee.setIdEmployee((int) cell.getNumericCellValue());
            } else if (cell.getCellType() == CellType.STRING) {
                employee.setIdEmployee(Integer.parseInt(cell.getStringCellValue()));
            }
        }

        // Paid Last Year in column 5
        cell = row.getCell(5);
        if (cell != null) {
            if (cell.getCellType() == CellType.NUMERIC) {
                employee.setPaidLastYear(String.valueOf(cell.getNumericCellValue()));
            } else if (cell.getCellType() == CellType.STRING) {
                employee.setPaidLastYear(cell.getStringCellValue());
            }
        }

        // Paid To Date in column 6
        cell = row.getCell(6);
        if (cell != null) {
            if (cell.getCellType() == CellType.NUMERIC) {
                employee.setPaidToDate(String.valueOf(cell.getNumericCellValue()));
            } else if (cell.getCellType() == CellType.STRING) {
                employee.setPaidToDate(cell.getStringCellValue());
            }
        }

        // PayRate in column 7
        cell = row.getCell(7);
        if (cell != null) {
            if (cell.getCellType() == CellType.NUMERIC) {
                employee.setPaidToDate(String.valueOf(cell.getNumericCellValue()));
            } else if (cell.getCellType() == CellType.STRING) {
                employee.setPayRate(cell.getStringCellValue());
            }
        }

        // PayRate ID in column 8
        cell = row.getCell(8);
        if (cell != null) {
            int idPayRate;
            if (cell.getCellType() == CellType.NUMERIC) {
                idPayRate = (int) cell.getNumericCellValue();
            } else if (cell.getCellType() == CellType.STRING) {
                idPayRate = Integer.parseInt(cell.getStringCellValue());
            } else {
                throw new IllegalStateException("Unexpected cell type in pay rate column: " + cell.getCellType());
            }

            PayRate payRate = payRateRepository.findById(idPayRate)
                    .orElseThrow(() -> new IllegalStateException("PayRate not found for ID: " + idPayRate));
            employee.setPayRates(payRate);
        }

        // Vacation Days in column 9
        cell = row.getCell(9);
        if (cell != null) {
            if (cell.getCellType() == CellType.NUMERIC) {
                employee.setVacationDays((int) cell.getNumericCellValue());
            } else if (cell.getCellType() == CellType.STRING) {
                employee.setVacationDays(Integer.parseInt(cell.getStringCellValue()));
            }
        }

        return employee;
    }

    private void updateEmployee(Employee existing, Employee updated) {
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setSsn(updated.getSsn());
        existing.setIdEmployee(updated.getIdEmployee());
        existing.setPaidLastYear(updated.getPaidLastYear());
        existing.setPaidToDate(updated.getPaidToDate());
        existing.setPayRates(updated.getPayRates());
        existing.setVacationDays(updated.getVacationDays());
    }


    @Transactional
    public void importEmployment(InputStream inputStream) {
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                // Skip header row (assuming it's the first row)
                if (row.getRowNum() == 0) {
                    continue;
                }

                Employment employment = createEmploymentFromRow(row);
                Optional<Employment> existingEmployee = employmentRepository.findById(employment.getEmploymentId());

                if (existingEmployee.isPresent()) {
                    // Update existing employee
                    Employment existing = existingEmployee.get();
                    updateEmployment(existing, employment);
                    employmentRepository.save(employment);
                } else {
                    // Save new employee
                    employmentRepository.save(employment);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error importing employment from Excel file", e);
        }
    }


    private Employment createEmploymentFromRow(Row row) {
        Employment employment = new Employment();

        // Set employmentId from column 0
        Cell cell = row.getCell(0);
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            employment.setEmploymentId((int) cell.getNumericCellValue());
        }

        // Set other properties accordingly from other columns
        employment.setEmploymentCode(getStringCellValue(row, 1));
        employment.setEmploymentStatus(getStringCellValue(row, 2));
        employment.setHireDateForWorking(getDateCellValue(row, 3));
        employment.setWorkersCompCode(getStringCellValue(row, 4));
        employment.setTerminationDate(getDateCellValue(row, 5));
        employment.setRehireDateForWorking(getDateCellValue(row, 6));
        employment.setLastReviewDate(getDateCellValue(row, 7));
        employment.setDaysWorkingPerMonth(getNumericCellValue(row, 8));

        // Set Personal
        int personalId = getNumericCellValue(row, 9).intValue();
        Optional<Personal> optionalPersonal = personRepository.findById(personalId);
        Personal personal = optionalPersonal.orElseThrow(() -> new IllegalStateException("Personal not found for ID: " + personalId));
        employment.setPersonal(personal);

        return employment;
    }

    private String getStringCellValue(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        if (cell != null && cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        }
        return null;
    }

    private Date getDateCellValue(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return cell.getDateCellValue();
        }
        return null;
    }

    private Integer getNumericCellValue(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        return null;
    }

    private void updateEmployment(Employment existing, Employment employment) {
        // Check and update each field of the existing Employment object with the new values
        existing.setEmploymentCode(employment.getEmploymentCode());
        existing.setEmploymentStatus(employment.getEmploymentStatus());
        existing.setHireDateForWorking(employment.getHireDateForWorking());
        existing.setWorkersCompCode(employment.getWorkersCompCode());
        existing.setTerminationDate(employment.getTerminationDate());
        existing.setRehireDateForWorking(employment.getRehireDateForWorking());
        existing.setLastReviewDate(employment.getLastReviewDate());
        existing.setDaysWorkingPerMonth(employment.getDaysWorkingPerMonth());
        existing.setPersonal(employment.getPersonal());
        employmentRepository.save(existing);
    }

    @Transactional
    public void importPersonal(InputStream inputStream) {
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                Personal personal = createPersonalFromRow(row);
                Optional<Personal> existingPersonal = personRepository.findById(personal.getPersonalId());

                if (existingPersonal.isPresent()) {
                    // Update existing employee
                    Personal existing = existingPersonal.get();
                    updatePersonal(existing, personal);
                    personRepository.save(personal);
                } else {
                    // Save new employee
                    personRepository.save(personal);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error importing employment from Excel file", e);
        }
    }

    private Personal createPersonalFromRow(Row row) {
        Personal personal = new Personal();

        // Set personalId from column 0
        Cell cell = row.getCell(0);
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            personal.setPersonalId((int) cell.getNumericCellValue());
        }

        // Set other properties accordingly from other columns
        personal.setFirstName(getStringCellValue(row, 1));
        personal.setLastName(getStringCellValue(row, 2));
        personal.setMiddleInitial(getStringCellValue(row, 3));
        personal.setBirthday(getDateCellValue(row, 4));
        personal.setSsn(getStringCellValue(row, 5));
        personal.setDriversLicense(getStringCellValue(row, 6));
        personal.setAddress1(getStringCellValue(row, 7));
        personal.setAddress2(getStringCellValue(row, 8));
        personal.setCity(getStringCellValue(row, 9));
        personal.setCountry(getStringCellValue(row, 10));
        personal.setZip(getNumericCellValue(row, 11));
        personal.setGender(getStringCellValue(row, 12));
        personal.setEmail(getStringCellValue(row, 13));
        personal.setPhoneNumber(getStringCellValue(row, 14));
        personal.setMaritalStatus(getStringCellValue(row, 15));
        personal.setEthnicity(getStringCellValue(row, 16));
        personal.setShareholderStatus(getBooleanCellValue(row, 17));

        // Set BenefitPlans
        int benefitPlansId = getNumericCellValue(row, 18).intValue();
        Optional<BenefitPlans> optionalBenefitPlans = benefitPlansRepository.findById(benefitPlansId);
        BenefitPlans benefitPlans = optionalBenefitPlans.orElseThrow(() -> new IllegalStateException("BenefitPlans not found for ID: " + benefitPlansId));
        personal.setBenefitPlans(benefitPlans);

        return personal;
    }

    private Boolean getBooleanCellValue(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        if (cell != null && cell.getCellType() == CellType.BOOLEAN) {
            return cell.getBooleanCellValue();
        }
        return null;
    }

    private void updatePersonal(Personal existing, Personal personal) {
        // Check and update each field of the existing Personal object with the new values
        existing.setFirstName(personal.getFirstName());
        existing.setLastName(personal.getLastName());
        existing.setMiddleInitial(personal.getMiddleInitial());
        existing.setBirthday(personal.getBirthday());
        existing.setSsn(personal.getSsn());
        existing.setDriversLicense(personal.getDriversLicense());
        existing.setAddress1(personal.getAddress1());
        existing.setAddress2(personal.getAddress2());
        existing.setCity(personal.getCity());
        existing.setCountry(personal.getCountry());
        existing.setZip(personal.getZip());
        existing.setGender(personal.getGender());
        existing.setEmail(personal.getEmail());
        existing.setPhoneNumber(personal.getPhoneNumber());
        existing.setMaritalStatus(personal.getMaritalStatus());
        existing.setEthnicity(personal.getEthnicity());
        existing.setShareholderStatus(personal.getShareholderStatus());
        existing.setBenefitPlans(personal.getBenefitPlans());
        personRepository.save(existing);
    }


}
