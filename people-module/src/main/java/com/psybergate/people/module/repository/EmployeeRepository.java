package com.psybergate.people.module.repository;


import com.psybergate.people.module.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Employee findByIdAndDeleted(UUID employeeId, boolean deleted);

    List<Employee> findAllByDeleted(boolean deleted);

}
