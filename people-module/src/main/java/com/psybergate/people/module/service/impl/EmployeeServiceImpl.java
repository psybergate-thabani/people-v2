package com.psybergate.people.module.service.impl;


import com.psybergate.people.module.entity.Employee;
import com.psybergate.people.module.entity.EmployeeStatus;
import com.psybergate.people.module.messaging.PeopleMessageResource;
import com.psybergate.people.module.repository.EmployeeRepository;
import com.psybergate.people.module.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private PeopleMessageResource peopleMessageResource;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PeopleMessageResource peopleMessageResource) {
        this.employeeRepository = employeeRepository;
        this.peopleMessageResource = peopleMessageResource;
    }

    @Override
    @Transactional
    public Employee createEmployee(@Valid Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> retrieveEmployees(Boolean deleted) {
        return employeeRepository.findAllByDeleted(deleted);
    }

    @Override
    public Employee retrieveEmployee(UUID employeeId) {
        return employeeRepository.findByIdAndDeleted(employeeId, false);
    }

    @Override
    @Transactional
    public Employee updateEmployee(@Valid Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(UUID employeeId) {
        Employee employee = retrieveEmployee(employeeId);
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public boolean validateEmployee(UUID employeeId) {
        Employee employee = employeeRepository.findByIdAndDeleted(employeeId, false);
        return Objects.nonNull(employee);
    }

    @Override
    @Transactional
    public Boolean isValid(UUID employeeId, Boolean deleted){
        Employee employee = employeeRepository.findByIdAndDeleted(employeeId, deleted);
        return !Objects.isNull(employee);
    }

    @Override
    @Transactional
    public Employee terminateEmployee(UUID employeeId) {
        Employee employee = retrieveEmployee(employeeId);
        if(EmployeeStatus.TERMINATED.equals(employee.getStatus())){
            throw new ValidationException("Employee already terminated.");
        }
        employee.setStatus(EmployeeStatus.TERMINATED);
        Employee terminatedEmployee = employeeRepository.save(employee);
        peopleMessageResource.broadcastTerminateEmployee(terminatedEmployee);
        return terminatedEmployee;
    }
}
