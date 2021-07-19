package com.pashkov.driverapi.app.repository;

import com.pashkov.driverapi.app.model.User;
import com.pashkov.driverapi.app.projection.UserProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = UserProjection.class)
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String name);
}
