package com.pashkov.driverapi.app.projection;

import com.pashkov.driverapi.app.model.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "user", types = User.class)
public interface UserProjection {
    String getUsername();
}
