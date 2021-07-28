package com.pashkov.driverapi.app.repository;

import com.pashkov.driverapi.app.model.Training;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.Set;

@RepositoryRestResource (exported = false)
public interface TrainingRepository  extends CrudRepository<Training, Long> {

    Set<Training> getByTopic_TopicDescription(String topicDescription);

    Optional<Training> getTrainingByTrainingTitle(String title);

    @Query(value = "SELECT * From training join users_complete_trainings on id = complete_trainings_id where users_id = ?1",
            nativeQuery = true)
    Set<Training> getUserCompleteTrainings(long userId);

}
