package org.group6.fastservices.services;

import org.group6.fastservices.data.models.Queue;

import java.util.List;

public interface QueueService {
    CreateQueueResponse createQueue(CreateQueueRequest request);
    Queue getQueueById(String id);
    List<Queue> getQueuesByOrganizationId(String organizationId);
    List<Queue> getQueuesByOfferingId(String offeringId);
    List<Queue> getAllQueues();
    Queue updateQueue(String id, Queue queue);
    void deleteQueue(String id);
}



package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.models.Offering;
import org.group6.fastservices.data.models.Queue;
import org.group6.fastservices.data.repositories.QueueRepository;
import org.group6.fastservices.dtos.requests.CreateQueueRequest;
import org.group6.fastservices.dtos.responses.CreateQueueResponse;
import org.group6.fastservices.exceptions.ResourceNotFoundException;
import org.group6.fastservices.services.QueueService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class QueueServiceImpl implements QueueService {

    private final QueueRepository queueRepository;
    private final ModelMapper modelMapper;

    @Override
    public CreateQueueResponse findOrCreateQueueForOffering(CreateQueueRequest request) {
        List<Queue> existingQueues = queueRepository.findByOrganizationId(request.getOrganizationId())
                .stream()
                .filter(q -> q.getOffering() != null &&
                        q.getOffering().getId().equals(request.getOfferingId()))
                .toList();

        if (!existingQueues.isEmpty()) {
            Queue existingQueue = existingQueues.get(0);
            return modelMapper.map(existingQueue, CreateQueueResponse.class)
                    .toBuilder()
                    .newlyCreated(false)
                    .message("Existing queue found for offering.")
                    .build();
        }

        // Otherwise, create new queue
        Queue newQueue = new Queue();
        newQueue.setName(request.getOfferingName() + " Queue");
        newQueue.setDescription("Queue for " + request.getOfferingName());

        Organization org = new Organization();
        org.setId(request.getOrganizationId());
        org.setName(request.getOrganizationName());
        newQueue.setOrganization(org);

        Offering offering = new Offering();
        offering.setId(request.getOfferingId());
        offering.setName(request.getOfferingName());
        newQueue.setOffering(offering);

        newQueue.setCreatedAt(LocalDateTime.now());
        Queue saved = queueRepository.save(newQueue);

        return modelMapper.map(saved, CreateQueueResponse.class)
                .toBuilder()
                .newlyCreated(true)
                .message("New queue created successfully.")
                .build();
    }

    // other methods...
}


}



