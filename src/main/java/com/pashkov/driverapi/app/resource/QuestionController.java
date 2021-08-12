package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.*;
import com.pashkov.driverapi.app.service.QuestionService;
import com.pashkov.driverapi.app.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Roman Pashkov
 */

@RestController
@RequestMapping(value = "/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final TopicService topicService;

    @Autowired
    QuestionRepresentationModelAssembler questionRepresentationModelAssembler;

    @Autowired
    TopicRepresentationModelAssembler topicRepresentationModelAssembler;

    public QuestionController(QuestionService questionService, TopicService topicService) {
        this.questionService = questionService;
        this.topicService = topicService;
    }

    @GetMapping(path = "/{id}/topic")
    public ResponseEntity<TopicModel> getQuestionTopic(@PathVariable long id){
        Optional<Question> questionById = questionService.getQuestionById(id);
        if(questionById.isEmpty()){
            throw new ResourceNotFoundException();
        }
        Topic topicModel = questionById.get().getTopicModel();
        return new ResponseEntity<>(topicRepresentationModelAssembler.toModel(topicModel), HttpStatus.OK);
    }

}
