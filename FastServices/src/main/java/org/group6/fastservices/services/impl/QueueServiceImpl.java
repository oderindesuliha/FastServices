package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.models.Queue;
import org.group6.fastservices.data.repositories.QueueRepository;
import org.group6.fastservices.dtos.requests.CreateQueueRequest;
import org.group6.fastservices.dtos.responses.CreateQueueResponse;
import org.group6.fastservices.services.QueueService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
                .filter(queue-> queue.getOffering() != null &&
                        queue.getOffering().getId().equals(request.getOfferingId()))
                .toList();

        if(!existingQueues.isEmpty()) {
            Queue existingQueue = existingQueues.get(0);
            return modelMapper.map(existingQueue, CreateQueueResponse.class)
                    .toBuilder()
                    .newlyCreated(false)
                    .message("Existing queue found for offering")
                    .build();
        }
        return null;
    }
}