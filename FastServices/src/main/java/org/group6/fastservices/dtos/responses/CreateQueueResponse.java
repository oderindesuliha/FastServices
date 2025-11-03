package org.group6.fastservices.dtos.responses;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CreateQueueResponse {
    private String id;

    private String name;
    private String description;

    private String organizationId;
    private String organizationName;

    private String offeringId;
    private String offeringName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private boolean newlyCreated;
    private String message;
}
