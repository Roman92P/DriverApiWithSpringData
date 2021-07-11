package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Training;
import com.pashkov.driverapi.app.repository.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Set<Training> getAllByTopicTitle(String topicTitle) {
        return trainingRepository.getByTopic_TopicDescription(topicTitle);
    }
}
