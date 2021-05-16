package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.TopicModel;

import java.util.Optional;
import java.util.Set;

public interface TopicService {

    Optional<TopicModel> getTopicByName(String TopicName);

    void addNewTopicModel(TopicModel topicModel);

    void removeTopicModel(TopicModel topicModel);

    Set<TopicModel> getAllTopic();
}
