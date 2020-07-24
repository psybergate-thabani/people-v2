package com.psybergate.people.module.messaging;

import com.psybergate.people.module.entity.Employee;

public interface PeopleMessageResource {
    void broadcastTerminateEmployee(Employee employee);
}
