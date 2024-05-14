package com.example.ceom.service.impl;

import com.example.ceom.entity.mysql.Employee;
import com.example.ceom.entity.sqlserver.Employment;
import com.example.ceom.entity.sqlserver.Personal;
import com.example.ceom.exception.NotFoundException;
import com.example.ceom.model.request.CreateEmployeeIntegration;
import com.example.ceom.repository.mysql.EmployeeRepository;
import com.example.ceom.repository.sqlserver.EmploymentRepository;
import com.example.ceom.repository.sqlserver.PersonRepository;
import com.example.ceom.service.EmployeeIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        int maxEmployeeNumber = employeeRepository.findMaxEmployeeNumber();
        int nextEmployeeNumber = (maxEmployeeNumber != 0) ? maxEmployeeNumber + 1 : 1;
        employee.setEmployeeNumber(nextEmployeeNumber);
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
        int maxPersonalId = personRepository.findMaxPersonalId();
        int nextPersonalId = (maxPersonalId != 0) ? maxPersonalId + 1 : 1;
        personal.setPersonalId(nextPersonalId);
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
        personal.setGender(request.getPersonal().getGender());
        personal.setEmail(request.getPersonal().getEmail());
        personal.setPhoneNumber(request.getPersonal().getPhoneNumber());
        personal.setMaritalStatus(request.getPersonal().getMaritalStatus());
        personal.setEthnicity(request.getPersonal().getEthnicity());
        personal.setShareholderStatus(request.getPersonal().getShareholderStatus());
        personal.setBenefitPlans(request.getPersonal().getBenefitPlans());
        personRepository.save(personal);

        Employment employment = new Employment();
        int maxEmploymentId = employmentRepository.findMaxEmploymentId();
        int nextEmploymentId = (maxEmploymentId != 0) ? maxEmploymentId + 1 : 1;
        employment.setEmploymentId(nextEmploymentId);
        employment.setEmploymentCode(request.getEmployment().getEmploymentCode());
        employment.setEmploymentStatus(request.getEmployment().getEmploymentStatus());
        employment.setHireDateForWorking(request.getEmployment().getHireDateForWorking());
        employment.setWorkersCompCode(request.getEmployment().getWorkersCompCode());
        employment.setTerminationDate(request.getEmployment().getTerminationDate());
        employment.setRehireDateForWorking(request.getEmployment().getRehireDateForWorking());
        employment.setLastReviewDate(request.getEmployment().getLastReviewDate());
        employment.setDaysWorkingPerMonth(request.getEmployment().getDaysWorkingPerMonth());
        employment.setPersonal(personal);
        employmentRepository.save(employment);
    }

    public void deleteEmployeeIntegration(Integer employeeNumber, Integer personalId, Integer employmentId) {
        personRepository.findById(personalId).ifPresent(personal -> personRepository.delete(personal));
        employmentRepository.findById(employmentId).ifPresent(employment -> employmentRepository.delete(employment));
        Employee employee = employeeRepository.findById(employeeNumber).orElseThrow(() -> new NotFoundException("Not Found"));
        employeeRepository.delete(employee);
    }

    @Override
    public void updateEmployeeIntegration(Integer employeeNumber, Integer personalId, Integer employmentId, CreateEmployeeIntegration request) {
        Employee employee = employeeRepository.findById(employeeNumber).orElseThrow(() -> new NotFoundException("Not Found employee"));
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

        Personal personal = personRepository.findById(personalId).orElseThrow(() -> new NotFoundException("Not Found personal"));
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
        personal.setGender(request.getPersonal().getGender());
        personal.setEmail(request.getPersonal().getEmail());
        personal.setPhoneNumber(request.getPersonal().getPhoneNumber());
        personal.setMaritalStatus(request.getPersonal().getMaritalStatus());
        personal.setEthnicity(request.getPersonal().getEthnicity());
        personal.setShareholderStatus(request.getPersonal().getShareholderStatus());
        personal.setBenefitPlans(request.getPersonal().getBenefitPlans());
        personRepository.save(personal);

        Employment employment = employmentRepository.findById(employmentId).orElseThrow(() -> new NotFoundException("Not Found employment"));
        employment.setEmploymentStatus(request.getEmployment().getEmploymentStatus());
        employment.setHireDateForWorking(request.getEmployment().getHireDateForWorking());
        employment.setWorkersCompCode(request.getEmployment().getWorkersCompCode());
        employment.setTerminationDate(request.getEmployment().getTerminationDate());
        employment.setRehireDateForWorking(request.getEmployment().getRehireDateForWorking());
        employment.setLastReviewDate(request.getEmployment().getLastReviewDate());
        employment.setDaysWorkingPerMonth(request.getEmployment().getDaysWorkingPerMonth());
        employment.setPersonal(personal);
        employmentRepository.save(employment);
    }

}
