package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Topic;

import java.util.Optional;
import java.util.Set;

public interface TopicService {

    Optional<Topic> getTopicByName(String TopicName);

    void addNewTopicModel(Topic topic);

    void removeTopicModel(Topic topic);

    Set<Topic> getAllTopic();
}
