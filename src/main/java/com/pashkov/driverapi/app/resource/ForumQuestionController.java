package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.ForumQuestion;
import com.pashkov.driverapi.app.model.ForumQuestionModel;
import com.pashkov.driverapi.app.model.ForumQuestionRepresentationModelAssembler;
import com.pashkov.driverapi.app.model.UserDetail;
import com.pashkov.driverapi.app.service.ForumQuestionService;
import com.pashkov.driverapi.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value="/forumQuestions")
public class ForumQuestionController {

    private final ForumQuestionService forumQuestionService;
    private final UserService userService;

    @Autowired
    private ForumQuestionRepresentationModelAssembler forumQuestionRepresentationModelAssembler;

    public ForumQuestionController(ForumQuestionService forumQuestionService, UserService userService) {
        this.forumQuestionService = forumQuestionService;
        this.userService = userService;
    }
    @PostMapping(produces = "application/json")
    public ResponseEntity<ForumQuestionModel> userSendForumQuestion
            (@RequestBody ForumQuestionModel forumQuestionModel, @AuthenticationPrincipal UsernamePasswordAuthenticationToken token){
        String name = token.getName();
        ForumQuestion forumQuestion = new ForumQuestion();
        forumQuestion.setText(forumQuestionModel.getText());
        forumQuestion.setUser(userService.findByUserName(name));
        forumQuestionService.postForumQuestion(forumQuestion);

        return ResponseEntity.ok(forumQuestionRepresentationModelAssembler.toModel(forumQuestion));

    }
}
