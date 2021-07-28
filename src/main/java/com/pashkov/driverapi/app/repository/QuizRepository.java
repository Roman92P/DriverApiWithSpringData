package com.pashkov.driverapi.app.repository;

import com.pashkov.driverapi.app.model.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface QuizRepository extends CrudRepository<Quiz, Long> {
}
