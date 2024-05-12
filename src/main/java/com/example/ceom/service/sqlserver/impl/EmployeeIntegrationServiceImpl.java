package com.example.ceom.service.sqlserver.impl;

import com.example.ceom.entity.mysql.Employee;
import com.example.ceom.entity.mysql.PayRate;
import com.example.ceom.entity.sqlserver.BenefitPlans;
import com.example.ceom.entity.sqlserver.Employment;
import com.example.ceom.entity.sqlserver.Personal;
import com.example.ceom.exception.NotFoundException;
import com.example.ceom.model.request.CreateEmployeeIntegration;
import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.repository.mysql.PayRateRepository;
import com.example.ceom.repository.sqlserver.BenefitPlansRepository;
import com.example.ceom.repository.sqlserver.EmploymentRepository;
import com.example.ceom.repository.sqlserver.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeIntegrationServiceImpl {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmploymentRepository employmentRepository;
    @Autowired
    private BenefitPlansRepository benefitPlansRepository;
    @Autowired
    private PayRateRepository payRateRepository;

    @Transactional
    public void saveEmployeeIntegration(CreateEmployeeIntegration request) {

        Personal personal = new Personal();
        List<Personal> personals = personRepository.findAll();
        int maxIdPersonal = personals.get(0).getPersonalId();
        for (Personal p : personals) {
            if (p.getPersonalId() > maxIdPersonal) {
                maxIdPersonal = p.getPersonalId();
            }
        }
        System.out.print(maxIdPersonal);
        personal.setPersonalId((maxIdPersonal + 1));
        personal.setFirstName(request.getPersonal().getFirstName());
        personal.setLastName(request.getPersonal().getLastName());
        personal.setMiddleInitial(request.getPersonal().getMiddleName());
        personal.setBirthday(request.getPersonal().getBirthday());
        personal.setSsn(request.getPersonal().getSsn());
        personal.setDriversLicense(request.getPersonal().getDriversLicense());
        personal.setAddress1(request.getPersonal().getAddress1());
        personal.setAddress2(request.getPersonal().getAddress2());
        personal.setCity(request.getPersonal().getCity());
        personal.setCountry(request.getPersonal().getCountry());
        personal.setZip(request.getPersonal().getZip());
        personal.setGender(request.getPersonal().isGender());
        personal.setEmail(request.getPersonal().getEmail());
        personal.setPhoneNumber(request.getPersonal().getPhoneNumber());
        personal.setMaritalStatus(request.getPersonal().getMaritalStatus());
        personal.setEthnicity(request.getPersonal().getEthnicity());
        personal.setShareholderStatus(request.getPersonal().isShareholderStatus());
        BenefitPlans benefitPlans = benefitPlansRepository.findById(request.getPersonal().getBenefitPlanId()).orElseThrow(() -> new NotFoundException("Not found benefit id"));
        personal.setBenefitPlans(benefitPlans);
        personRepository.save(personal);

        // Create Employee
        Employee employee = new Employee();
        List<Employee> employees = employeeRepository.findAll();
        int maxIdEmployee = employees.get(0).getIdEmployee();
        for (Employee e : employees) {
            if (e.getIdEmployee() > maxIdEmployee) {
                maxIdEmployee = e.getIdEmployee();
            }
        }
        System.out.print(maxIdEmployee);
        employee.setEmployeeNumber(maxIdEmployee + 1);
        employee.setFirstName(request.getPersonal().getFirstName());
        employee.setLastName(request.getPersonal().getLastName() + request.getPersonal().getMiddleName());
        employee.setIdEmployee(request.getEmployee().getIdEmployee());
        employee.setPaidLastYear(request.getEmployee().getPaidLastYear());
        employee.setPaidToDate(request.getEmployee().getPaidToDate());
        employee.setPayRate(request.getEmployee().getPayRate());
        PayRate payRate = payRateRepository.findById(request.getEmployee().getIdPayRate()).orElseThrow(() -> new NotFoundException("Not found payRate id"));
        employee.setPayRates(payRate);
        employee.setSsn(request.getEmployee().getSsn());
        employee.setVacationDays(request.getEmployee().getVacationDays());
        employeeRepository.save(employee);

        Employment employment = new Employment();
        List<Employment> employeeList = employmentRepository.findAll();
        int maxIdEmployment = employeeList.get(0).getEmploymentId();
        for (Employment e : employeeList) {
            if (e.getEmploymentId() > maxIdEmployment) {
                maxIdEmployment = e.getEmploymentId();
            }
        }
        System.out.print(maxIdEmployment);
        employment.setEmploymentId(maxIdEmployment + 1);
        employment.setEmploymentCode(request.getEmployment().getEmploymentCode());
        employment.setEmploymentStatus(request.getEmployment().getEmploymentStatus());
        employment.setHireDateForWorking(request.getEmployment().getHireDateForWorking());
        employment.setWorkersCompCode(request.getEmployment().getWorkersCompCode());
        employment.setTerminationDate(request.getEmployment().getTerminationDate());
        employment.setRehireDateForWorking(request.getEmployment().getRehireDateForWorking());
        employment.setLastReviewDate(request.getEmployment().getLastReviewDate());
        employment.setDaysWorkingPerMonth(request.getEmployment().getDaysWorkingPerMonth());
        employment.setPersonal(request.getEmployment().getPersonal());
        employmentRepository.save(employment);
    }
}
