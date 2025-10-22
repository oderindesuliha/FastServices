package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.dtos.requests.RegisterOrgRequest;
import org.group6.fastservices.dtos.responses.RegisterOrgResponse;
import org.group6.fastservices.services.AdminService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Override
    public RegisterOrgResponse registerOrg(RegisterOrgRequest register) {
        return new RegisterOrgResponse();
    }

    private void verifyNewEmail(String email) {
        if()
    }
}
