package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.models.Offering;
import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.models.Queue;
import org.group6.fastservices.data.repositories.QueueRepository;
import org.group6.fastservices.dtos.requests.CreateQueueRequest;
import org.group6.fastservices.dtos.responses.CreateQueueResponse;
import org.group6.fastservices.exceptions.QueueNotFoundException;
import org.group6.fastservices.services.OfferingService;
import org.group6.fastservices.services.OrganizationService;
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
    private final OfferingService offeringService;
    private final OrganizationService organizationService;

    @Override
    public CreateQueueResponse findOrCreateQueueForOffering(CreateQueueRequest request) {
        List<Queue> existingQueues = queueRepository.findByOrganizationId(request.getOrganizationId())
                .stream()
                .filter(queue-> queue.getOffering() != null &&
                        queue.getOffering().getId().equals(request.getOfferingId()))
                .toList();

        if(!existingQueues.isEmpty()) {
            Queue existingQueue = existingQueues.getFirst();
            return modelMapper.map(existingQueue, CreateQueueResponse.class)
                    .toBuilder()
                    .newlyCreated(false)
                    .message("Existing queue found for offering")
                    .build();
        }
        Queue newQueue = buildNewQueue(request);
        Queue savedQueue = queueRepository.save(newQueue);

        return modelMapper.map(savedQueue, CreateQueueResponse.class)
                .toBuilder()
                .newlyCreated(true)
                .message("New queue created successfully")
                .build();
    }

    @Override
    public Queue getQueueById(String id) {
        return queueRepository.findById(id)
                .orElseThrow(() -> new QueueNotFoundException("Queue not found"));
    }

    private Queue buildNewQueue(CreateQueueRequest request) {
        Queue queue = new Queue();
        queue.setName(request.getOfferingName() + " Queue");
        queue.setDescription("Queue for " + request.getOfferingName());

        Organization org = organizationService.getOr;
        org.setId(request.getOrganizationId());
        org.setCode(request.getOrganizationCode());
        queue.setOrganization(org);

        Offering offering = offeringService.getOfferingById(request.getOfferingId());
        queue.setOffering(offering);
        queue.setCreatedAt(LocalDateTime.now());
        return queue;
    }
}