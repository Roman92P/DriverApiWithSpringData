package com.pashkov.driverapi.app.repository;

import com.pashkov.driverapi.app.model.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "topics", path = "topics")
public interface TopicRepository extends CrudRepository<Topic, Long> {

     Optional<Topic> findByTopicDescription(@Param("topicDescription")String topicDescription);

     @Override
     @RestResource(exported = false)
     @ApiIgnore
     Optional<Topic> findById(Long aLong);
}



