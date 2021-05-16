package com.pashkov.driverapi.app.repository;

import com.pashkov.driverapi.app.model.AdviceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdviceRepository extends JpaRepository<AdviceModel,Long> {
}
