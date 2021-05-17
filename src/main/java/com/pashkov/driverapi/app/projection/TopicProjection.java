package com.pashkov.driverapi.app.projection;

import com.pashkov.driverapi.app.model.Topic;
import org.springframework.data.rest.core.config.Projection;

@Projection(
        name = "Topic",
        types = { Topic.class })
public interface TopicProjection {
    String getTopicDescription();
}
