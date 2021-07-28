package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.ForumQuestion;

import java.util.Optional;
import java.util.Set;

public interface ForumQuestionService {

    public void postForumQuestion(ForumQuestion forumQuestion);

    Optional<ForumQuestion> getForumQuestionByid(Long id);

    void updateForumQuestion(ForumQuestion forumQuestion1);

    void deleteForumQuestion(long forumQuestionId);

    Set<ForumQuestion> getAllForumQuestions();
}
