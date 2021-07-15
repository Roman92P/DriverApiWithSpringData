package com.pashkov.driverapi.app.repository;

import com.pashkov.driverapi.app.model.Training;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@RepositoryRestResource
public interface TrainingRepository  extends CrudRepository<Training, Long> {

    Set<Training> getByTopic_TopicDescription(String topicDescription);

    Optional<Training> getTrainingByTrainingTitle(String title);

}
