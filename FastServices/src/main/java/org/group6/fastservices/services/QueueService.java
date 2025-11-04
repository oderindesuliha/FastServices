package org.group6.fastservices.services;

import org.group6.fastservices.data.models.Queue;
import org.group6.fastservices.dtos.requests.CreateQueueRequest;
import org.group6.fastservices.dtos.responses.CreateQueueResponse;

public interface QueueService {
    CreateQueueResponse findOrCreateQueueForOffering(CreateQueueRequest request);
    Queue getQueueById(String id);
//    List<Queue> getQueuesByOrganizationId(String organizationId);
//    List<Queue> getQueuesByOfferingId(String offeringId);
//    List<Queue> getAllQueues();
//    Queue updateQueue(String id, Queue queue);
//    void deleteQueue(String id);
}
