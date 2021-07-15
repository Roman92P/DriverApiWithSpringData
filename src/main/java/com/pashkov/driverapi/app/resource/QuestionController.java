package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.QuestionRepresentationModelAssembler;
import com.pashkov.driverapi.app.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/questions")
public class QuestionController {
    
    private final QuestionService questionService;
    
    @Autowired
    QuestionRepresentationModelAssembler questionRepresentationModelAssembler;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    
    
}
