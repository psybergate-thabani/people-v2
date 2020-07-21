package com.psybergate.people.api.resource.impl;

import com.psybergate.people.api.resource.PeopleApi;
import com.psybergate.people.api.resource.dto.ValidationDTO;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public class PeopleApiRestImpl implements PeopleApi {

    public static final String VALIDATE_EMPLOYEE_URL = "%s/api/people/v1/employees/%s/validate";

    @Override
    public ValidationDTO validateEmployee(UUID employeeId, String serverDomain) {
        RestTemplate restTemplate = new RestTemplate();
        ValidationDTO validateEmployee = restTemplate.getForObject(String.format(VALIDATE_EMPLOYEE_URL, serverDomain, employeeId), ValidationDTO.class);
        return validateEmployee;
    }
}
