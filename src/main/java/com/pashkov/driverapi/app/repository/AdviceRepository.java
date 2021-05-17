package com.pashkov.driverapi.app.repository;

import com.pashkov.driverapi.app.model.Advice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdviceRepository extends CrudRepository<Advice,Long> {
}
