package com.pashkov.driverapi.app.projection;

import com.pashkov.driverapi.app.model.ForumQuestion;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="onlyTextForumQuestion",types = ForumQuestion.class)
public interface ForumQuestionProjection {

    String getText();
}
