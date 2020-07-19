package com.psybergate.people.module.controller;

import com.psybergate.people.module.dto.ValidationDTO;
import com.psybergate.people.module.entity.Employee;
import com.psybergate.people.module.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@RequestMapping(path = "api/people")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("v1/employees")
    public ResponseEntity<Employee> captureEmployee(@RequestBody @Valid Employee employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    @GetMapping(value = "v1/employees/{employeeId}/validate")
    public ResponseEntity<ValidationDTO> validateEmployee(@PathVariable UUID employeeId){
        return ResponseEntity.ok(employeeService.validateEmployee(employeeId));
    }

    @PutMapping("v1/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid Employee employee, @PathVariable UUID employeeId) {

        if ((employee == null || employeeId == null) || !employeeId.equals(employee.getId()))
            throw new ValidationException("id in url path must match employee id in request body");

        return ResponseEntity.ok(employeeService.updateEmployee(employee));
    }

    @GetMapping(value = "v1/employees", params = {"deleted"})
    public ResponseEntity<List<Employee>> retrieveEmployees(@RequestParam("deleted") boolean deleted) {
        return ResponseEntity.ok(employeeService.retrieveEmployees(deleted));
    }

    @GetMapping(value = "v1/employees/{employeeId}/validate", params = {"deleted"})
    public ResponseEntity<ValidationDTO> validateEmployee(@PathVariable UUID employeeId, @RequestParam("deleted") Boolean deleted) {
        return ResponseEntity.ok(employeeService.validateEmployee(employeeId, deleted));
    }

    @GetMapping("v1/employees/{employeeId}")
    public ResponseEntity<Employee> retrieveEmployee(@PathVariable UUID employeeId) {
        Employee employee = employeeService.retrieveEmployee(employeeId);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("v1/employees/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok().build();
    }
}
