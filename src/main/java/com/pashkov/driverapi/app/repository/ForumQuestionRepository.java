package com.pashkov.driverapi.app.repository;

import com.pashkov.driverapi.app.model.ForumQuestion;
import com.pashkov.driverapi.app.projection.ForumQuestionProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = ForumQuestionProjection.class, exported = false)
public interface ForumQuestionRepository extends CrudRepository<ForumQuestion,Long> {

}
