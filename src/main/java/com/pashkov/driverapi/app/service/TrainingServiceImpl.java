package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Training;
import com.pashkov.driverapi.app.model.User;
import com.pashkov.driverapi.app.repository.TrainingRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

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

    @Override
    public List<Training> getAllTrainings() {
        Iterable<Training> all = trainingRepository.findAll();
        List<Training> trainings = new ArrayList<>();
        all.forEach(trainings::add);
        return trainings;
    }

    @Override
    public Optional<Training> getTrainingByTitle(String title) {
        Optional<Training> trainingByTrainingTitle = trainingRepository.getTrainingByTrainingTitle(title);
        if (trainingByTrainingTitle.isEmpty()) throw new EntityNotFoundException();
        return trainingByTrainingTitle;
    }

    @Override
    public Set<Training> completeUserTrainings(long userId) {
        return trainingRepository.getUserCompleteTrainings(userId);
    }

    @Override
    public Set<Training> getIncompleatedUser(User byUserName) {
        return null;
    }

    @Override
    public Optional<Training> getTrainingById(long id) {
        return trainingRepository.findById(id);
    }
}
