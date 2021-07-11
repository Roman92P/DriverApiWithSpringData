package com.pashkov.driverapi.app.repository;

import com.pashkov.driverapi.app.model.Training;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.Set;

@RepositoryRestResource
public interface TrainingRepository  extends CrudRepository<Training, Long> {

    Set<Training> getByTopic_TopicDescription(String topicDescription);

}
