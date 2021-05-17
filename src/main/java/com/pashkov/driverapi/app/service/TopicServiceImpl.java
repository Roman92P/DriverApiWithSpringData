package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.Topic;
import com.pashkov.driverapi.app.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TopicServiceImpl implements TopicService{


    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Optional<Topic> getTopicByName(String topicName) {
        return topicRepository.findByTopicDescription(topicName);
    }

    @Override
    public void addNewTopicModel(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public void removeTopicModel(Topic topic) {
        topicRepository.delete(topic);
    }

    @Override
    public Set<Topic> getAllTopic() {
        Iterable<Topic> all = topicRepository.findAll();
        Set<Topic> collect = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toSet());
        return collect;
    }
}
