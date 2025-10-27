package org.group6.fastservices.dtos.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueueRequest {
    private String name;
    private String description;
    private String organizationId;
}
