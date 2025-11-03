package org.group6.fastservices.services;

import org.group6.fastservices.data.models.Queue;

import java.util.List;

public interface QueueService {
    Queue createQueue(Queue queue);
    Queue getQueueById(String id);
    List<Queue> getQueuesByOrganizationId(String organizationId);
    List<Queue> getQueuesByOfferingId(String offeringId);
    List<Queue> getAllQueues();
    Queue updateQueue(String id, Queue queue);
    void deleteQueue(String id);
}



package org.group6.fastservices.services.impl;

import lombok.AllArgsConstructor;
import org.group6.fastservices.data.models.Offering;
import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.models.Queue;
import org.group6.fastservices.data.repositories.QueueRepository;
import org.group6.fastservices.exceptions.ResourceNotFoundException;
import org.group6.fastservices.services.QueueService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class QueueServiceImpl implements QueueService {

    private final QueueRepository queueRepository;

    @Override
    public Queue findOrCreateQueueForOffering(Offering offering) {
        // Try to find an existing queue for the offering
        List<Queue> existingQueues = queueRepository.findByOrganizationId(offering.getOrganization().getId())
                .stream()
                .filter(q -> q.getName().equalsIgnoreCase(offering.getName()))
                .toList();

        if (!existingQueues.isEmpty()) {
            return existingQueues.get(0);
        }

        // Otherwise, create a new queue
        Queue newQueue = new Queue();
        newQueue.setName(offering.getName() + " Queue");
        newQueue.setDescription("Queue for " + offering.getName());
        newQueue.setOrganization(offering.getOrganization());
        newQueue.setCreatedAt(LocalDateTime.now());

        return queueRepository.save(newQueue);
    }

}



