package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Training;

import java.util.Set;

public interface TrainingService {

    Set<Training> getAllByTopicTitle(String topicTitle);
}
