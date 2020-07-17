package com.psybergate.people.module.messaging;

import com.psybergate.people.module.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeMessage {
    private EventType eventType;

    private Employee employee;
}
