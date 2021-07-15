package com.pashkov.driverapi.app.repository;

import com.pashkov.driverapi.app.model.Question;
import com.pashkov.driverapi.app.projection.QuestionProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(excerptProjection = QuestionProjection.class, exported = false)
public interface QuestionRepository extends CrudRepository<Question, Long> {
    Optional<Question> findByTopic_TopicDescription(String topicName);
}
