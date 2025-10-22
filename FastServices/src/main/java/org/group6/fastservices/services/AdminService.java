package org.group6.fastservices.services;

import org.group6.fastservices.dtos.requests.RegisterOrgRequest;
import org.group6.fastservices.dtos.responses.RegisterOrgResponse;

public interface AdminService {

    RegisterOrgResponse registerOrg(RegisterOrgRequest register);
}
