package com.psybergate.people.module.service.impl;


import com.psybergate.people.module.dto.ValidationDTO;
import com.psybergate.people.module.entity.Employee;
import com.psybergate.people.module.repository.EmployeeRepository;
import com.psybergate.people.module.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(@Valid EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
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
    public ValidationDTO validateEmployee(UUID employeeId) {
        Employee employee = employeeRepository.findByIdAndDeleted(employeeId, false);
        return new ValidationDTO(Objects.nonNull(employee));
    }

    @Override
    @Transactional
    public ValidationDTO validateEmployee(UUID employeeId, Boolean deleted) {
        Employee employee = employeeRepository.findByIdAndDeleted(employeeId, deleted);
        return new ValidationDTO(Objects.nonNull(employee));
    }
}
