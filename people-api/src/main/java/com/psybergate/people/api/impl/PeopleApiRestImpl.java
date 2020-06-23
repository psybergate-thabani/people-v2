package com.psybergate.people.api.impl;

import com.psybergate.people.api.PeopleApi;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.UUID;

@Component
public class PeopleApiRestImpl implements PeopleApi {

    public static final String VALIDATE_EMPLOYEE_URL = "%s/api/people/v1/employees/%s/valid";

    @Override
    public boolean validateEmployee(UUID employeeId, String serverDomain) {
        RestTemplate restTemplate = new RestTemplate();
        String stringValue = restTemplate.getForObject(String.format(VALIDATE_EMPLOYEE_URL, serverDomain, employeeId), String.class);
        System.out.println("Rest Template Results " + stringValue);
        Boolean employeeExists = Boolean.valueOf(stringValue);
//        if (Objects.isNull(employeeExists))
//            throw new RuntimeException("Unexpected result from server");
        return false;
    }
}
