package org.group6.fastservices.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class OrganizationResponse {
    private String id;
    private String name;
    private String code;
    private String address;
    private String contactEmail;
    private String contactPhone;
    private boolean verified;
    private LocalDateTime createdAt;
    private List<OfferingResponse> services;
    private List<QueueResponse> queues;
}