package com.pashkov.driverapi.app.repository;

import com.pashkov.driverapi.app.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findRoleByName(String role_user);
}
