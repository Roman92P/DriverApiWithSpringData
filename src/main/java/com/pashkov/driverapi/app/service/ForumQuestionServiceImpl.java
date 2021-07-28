package com.pashkov.driverapi.app.service;

import com.pashkov.driverapi.app.model.ForumQuestion;
import com.pashkov.driverapi.app.repository.ForumQuestionRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public void deleteForumQuestion(long forumQuestionId) {
        ForumQuestion forumQuestion = forumQuestionRepository.findById(forumQuestionId)
                .orElseThrow(EntityNotFoundException::new);
        forumQuestionRepository.delete(forumQuestion);
    }

    @Override
    public Set<ForumQuestion> getAllForumQuestions() {
        Iterable<ForumQuestion> all = forumQuestionRepository.findAll();
        Set<ForumQuestion> resultSet = new HashSet<>();
        for ( ForumQuestion f: all ) {
            resultSet.add(f);
        }
        return resultSet;
    }
}
