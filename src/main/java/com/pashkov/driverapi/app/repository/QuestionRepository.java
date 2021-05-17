package com.pashkov.driverapi.app.repository;

import com.pashkov.driverapi.app.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface QuestionRepository extends CrudRepository<Question, Long> {
}
