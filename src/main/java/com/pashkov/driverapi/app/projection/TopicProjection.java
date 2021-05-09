package com.pashkov.driverapi.app.projection;

import com.pashkov.driverapi.app.model.TopicModel;
import org.springframework.data.rest.core.config.Projection;

import java.awt.print.Book;

@Projection(
        name = "Topic",
        types = { TopicModel.class })
public interface TopicProjection {
    String getTopicDescription();
}
