package org.group6.fastservices.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";

}