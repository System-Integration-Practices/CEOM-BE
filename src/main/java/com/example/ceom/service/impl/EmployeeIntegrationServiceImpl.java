package com.example.ceom.service.impl;

import com.example.ceom.entity.mysql.Employee;
import com.example.ceom.entity.sqlserver.Employment;
import com.example.ceom.entity.sqlserver.Personal;
import com.example.ceom.exception.NotFoundException;
import com.example.ceom.model.reponse.MessageResponse;
import com.example.ceom.model.request.CreateEmployeeIntegration;
import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.repository.sqlserver.EmploymentRepository;
import com.example.ceom.repository.sqlserver.PersonRepository;
import com.example.ceom.service.EmployeeIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeIntegrationServiceImpl implements EmployeeIntegrationService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmploymentRepository employmentRepository;

    @Transactional
    public void saveEmployeeIntegration(CreateEmployeeIntegration request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setIdEmployee(request.getEmployee().getIdEmployee());
        employee.setPaidLastYear(request.getEmployee().getPaidLastYear());
        employee.setPaidToDate(request.getEmployee().getPaidToDate());
        employee.setPayRate(request.getEmployee().getPayRate());
        employee.setPayRates(request.getEmployee().getPayRates());
        employee.setSsn(request.getEmployee().getSsn());
        employee.setVacationDays(request.getEmployee().getVacationDays());
        employeeRepository.save(employee);

        Personal personal = new Personal();
        personal.setFirstName(request.getFirstName());
        personal.setLastName(request.getLastName());
        personal.setMiddleInitial(request.getPersonal().getMiddleInitial());
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
        personal.setBenefitPlans(request.getPersonal().getBenefitPlans());
        personRepository.save(personal);

        Employment employment=new Employment();
        employment.setEmploymentCode(request.getEmployment().getEmploymentCode());
        employment.setEmploymentStatus(request.getEmployment().getEmploymentStatus());
        employment.setHireDateForWorking(request.getEmployment().getHireDateForWorking());
        employment.setWorkersCompCode(request.getEmployment().getWorkersCompCode());
        employment.setTerminationDate(request.getEmployment().getTerminationDate());
        employment.setRehireDateForWorking(request.getEmployment().getRehireDateForWorking());
        employment.setLastReviewDate(request.getEmployment().getLastReviewDate());
        employment.setDaysWorkingPerMonth(request.getEmployment().getDaysWorkingPerMonth());
        employment.setPersonal(personRepository.findLastPersonal());
        employmentRepository.save(employment);
    }
    @Transactional
    public void deleteEmployeeIntegration(Integer employeeNumber, Integer personalId, Integer employmentId) {

        Employee employee = employeeRepository.findById(employeeNumber).orElseThrow(() -> new NotFoundException("Not found employee number" + employeeNumber));
        if (employee != null) {
            employeeRepository.delete(employee);
        }
        Personal personal = personRepository.findById(personalId).orElseThrow(() -> new NotFoundException("Not found personal id" + personalId));
            if (personal != null) {
            personRepository.delete(personal);
        }

        Employment employment = employmentRepository.findById(employmentId).orElse(null);
        if (employment != null) {
            employmentRepository.delete(employment);
        }
    }

    @Override
    public void updateEmployeeIntegration(CreateEmployeeIntegration request, Integer employeeNumber, Integer personalId, Integer employmentId) {
        Employee employee;
        employee = employeeRepository.findById(request.getEmployee().getIdEmployee()).orElseThrow(() -> new NotFoundException("error"));
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setIdEmployee(request.getEmployee().getIdEmployee());
        employee.setPaidLastYear(request.getEmployee().getPaidLastYear());
        employee.setPaidToDate(request.getEmployee().getPaidToDate());
        employee.setPayRate(request.getEmployee().getPayRate());
        employee.setPayRates(request.getEmployee().getPayRates());
        employee.setSsn(request.getEmployee().getSsn());
        employee.setVacationDays(request.getEmployee().getVacationDays());
        employeeRepository.save(employee);

        Personal personal;
        personal = personRepository.findById(request.getPersonal().getPersonalId()).orElseThrow(() -> new NotFoundException("error"));;

        personal.setFirstName(request.getFirstName());
        personal.setLastName(request.getLastName());
        personal.setMiddleInitial(request.getPersonal().getMiddleInitial());
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
        personal.setBenefitPlans(request.getPersonal().getBenefitPlans());
        personRepository.save(personal);

        Employment employment = employmentRepository.findById(request.getEmployment().getEmploymentId()).orElseThrow(() -> new NotFoundException("error"));;
        employment.setEmploymentCode(request.getEmployment().getEmploymentCode());
        employment.setEmploymentStatus(request.getEmployment().getEmploymentStatus());
        employment.setHireDateForWorking(request.getEmployment().getHireDateForWorking());
        employment.setWorkersCompCode(request.getEmployment().getWorkersCompCode());
        employment.setTerminationDate(request.getEmployment().getTerminationDate());
        employment.setRehireDateForWorking(request.getEmployment().getRehireDateForWorking());
        employment.setLastReviewDate(request.getEmployment().getLastReviewDate());
        employment.setDaysWorkingPerMonth(request.getEmployment().getDaysWorkingPerMonth());
        employment.setPersonal(personRepository.findLastPersonal());
        employmentRepository.save(employment);
    }

}
