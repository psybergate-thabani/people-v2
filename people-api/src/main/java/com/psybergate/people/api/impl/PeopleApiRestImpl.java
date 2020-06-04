package com.psybergate.people.api.impl;

import com.psybergate.people.api.PeopleApi;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.UUID;

@Component
public class PeopleApiRestImpl implements PeopleApi {

    public static final String VALIDATE_EMPLOYEE_URL = "http://localhost:8080/api/people/v1/employees/%s/valid?deleted=false";

    @Override
    public boolean validateEmployee(UUID employeeId) {
        RestTemplate restTemplate = new RestTemplate();
        Boolean employeeExists = restTemplate.getForObject(String.format(VALIDATE_EMPLOYEE_URL, employeeId), Boolean.TYPE);
        if (Objects.isNull(employeeExists))
            throw new RuntimeException("Unexpected result from server");
        return employeeExists;
    }
}
