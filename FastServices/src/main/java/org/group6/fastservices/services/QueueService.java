package org.group6.fastservices.services;

import org.group6.fastservices.data.models.Queue;
import org.group6.fastservices.dtos.requests.CreateQueueRequest;
import org.group6.fastservices.dtos.responses.CreateQueueResponse;

import java.util.List;

public interface QueueService {
    CreateQueueResponse findOrCreateQueueForOffering(CreateQueueRequest request);
//    Queue getQueueById(String id);
//    List<Queue> getQueuesByOrganizationId(String organizationId);
//    List<Queue> getQueuesByOfferingId(String offeringId);
//    List<Queue> getAllQueues();
//    Queue updateQueue(String id, Queue queue);
//    void deleteQueue(String id);
}

//
//        // Otherwise, create new queue
//        Queue newQueue = new Queue();
//        newQueue.setName(request.getOfferingName() + " Queue");
//        newQueue.setDescription("Queue for " + request.getOfferingName());
//
//        Organization org = new Organization();
//        org.setId(request.getOrganizationId());
//        org.setName(request.getOrganizationName());
//        newQueue.setOrganization(org);
//
//        Offering offering = new Offering();
//        offering.setId(request.getOfferingId());
//        offering.setName(request.getOfferingName());
//        newQueue.setOffering(offering);
//
//        newQueue.setCreatedAt(LocalDateTime.now());
//        Queue saved = queueRepository.save(newQueue);
//
//        return modelMapper.map(saved, CreateQueueResponse.class)
//                .toBuilder()
//                .newlyCreated(true)
//                .message("New queue created successfully.")
//                .build();
//    }
//
//    // other methods...
//}





