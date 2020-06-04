package com.psybergate.people.module.service;


import com.psybergate.people.module.entity.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<Employee> retrieveEmployees(Boolean deleted);

    Employee retrieveEmployee(UUID employeeId);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(UUID employeeId);

    boolean validateEmployee(UUID employeeId, boolean deleted);
}
