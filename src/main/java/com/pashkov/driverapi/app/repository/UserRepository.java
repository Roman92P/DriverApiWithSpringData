package com.pashkov.driverapi.app.repository;

import com.pashkov.driverapi.app.model.Advice;
import com.pashkov.driverapi.app.model.User;
import com.pashkov.driverapi.app.projection.UserProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.Set;

@RepositoryRestResource(excerptProjection = UserProjection.class, exported = false)
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String name);

    @Query("select u from User u join fetch u.likedAdvices a where u.id = :id")
    Optional<User>getUserByIdAndLikedAdvices(Long id);

}
