package com.psybergate.people.api.impl;

import com.psybergate.people.api.PeopleApi;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class PeopleApiRestImpl implements PeopleApi {


    public static final String VALIDATE_EMPLOYEE_URL = "%s/api/people/v1/employees/%s/validate";

    @Override
    public boolean validateEmployee(UUID employeeId, String serverDomain) {
        RestTemplate restTemplate = new RestTemplate();
        Boolean isValid = restTemplate.getForObject(String.format(VALIDATE_EMPLOYEE_URL, serverDomain, employeeId), Boolean.class);
        return isValid;
    }
}
