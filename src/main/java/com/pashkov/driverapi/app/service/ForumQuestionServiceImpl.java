package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.ForumQuestion;
import com.pashkov.driverapi.app.repository.ForumQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class ForumQuestionServiceImpl implements ForumQuestionService {

    private final ForumQuestionRepository forumQuestionRepository;

    public ForumQuestionServiceImpl(ForumQuestionRepository forumQuestionRepository) {
        this.forumQuestionRepository = forumQuestionRepository;
    }

    @Override
    public void postForumQuestion(ForumQuestion forumQuestion) {
        forumQuestionRepository.save(forumQuestion);
    }

    @Override
    public Optional<ForumQuestion> getForumQuestionByid(Long id) {
        return forumQuestionRepository.findById(id);
    }

    @Override
    public void updateForumQuestion(ForumQuestion forumQuestion1) {
        forumQuestionRepository.save(forumQuestion1);
    }
}
