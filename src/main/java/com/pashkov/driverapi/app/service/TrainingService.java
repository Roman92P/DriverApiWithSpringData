package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Training;
import com.pashkov.driverapi.app.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TrainingService {

    Set<Training> getAllByTopicTitle(String topicTitle);

    List<Training> getAllTrainings();

    Optional<Training> getTrainingByTitle(String title);

    Set<Training> completeUserTrainings(long userId);

    Set<Training> getIncompleatedUser(User byUserName);
}
