package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.TopicModel;
import com.pashkov.driverapi.app.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService{


    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Optional<TopicModel> getTopicByName(String topicName) {
        return topicRepository.findByTopicDescription(topicName);
    }

    @Override
    public void addNewTopicModel(TopicModel topicModel) {
        topicRepository.save(topicModel);
    }

    @Override
    public void removeTopicModel(TopicModel topicModel) {
        topicRepository.delete(topicModel);
    }

    @Override
    public Set<TopicModel> getAllTopic() {
        Set<TopicModel> collect = topicRepository.findAll().stream().collect(Collectors.toSet());
        return collect;
    }
}
