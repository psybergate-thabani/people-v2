package com.psybergate.people.api.resource;

import com.psybergate.people.api.resource.dto.ValidationDTO;

import java.util.UUID;

public interface PeopleApi {
    ValidationDTO validateEmployee(UUID employeeId, String serverDomain);
}
