package com.pashkov.driverapi.app.repository;

import com.pashkov.driverapi.app.model.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface AdviceRepository extends CrudRepository<Advice,Long> {

    Optional<Advice> findAdviceByAdviceTitle(String title);


}
