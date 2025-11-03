package org.group6.fastservices.services.impl;

import org.group6.fastservices.data.models.Queue;
import org.group6.fastservices.data.repositories.QueueRepository;
import org.group6.fastservices.dtos.requests.CreateQueueRequest;
import org.group6.fastservices.dtos.responses.CreateQueueResponse;
import org.group6.fastservices.exceptions.ResourceNotFoundException;
import org.group6.fastservices.services.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QueueServiceImpl implements QueueService {

    @Override
    public CreateQueueResponse findOrCreateQueueForOffering(CreateQueueRequest request) {
        return null;
    }
}