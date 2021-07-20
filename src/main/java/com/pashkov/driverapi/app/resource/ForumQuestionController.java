package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.model.*;
import com.pashkov.driverapi.app.service.ForumQuestionService;
import com.pashkov.driverapi.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping(value="/forumQuestions")
public class ForumQuestionController {

    private final Logger logger = LoggerFactory.getLogger(ForumQuestionController.class);

    private final ForumQuestionService forumQuestionService;
    private final UserService userService;

    private final UserDetail userDetail;


    @Autowired
    private ForumQuestionRepresentationModelAssembler forumQuestionRepresentationModelAssembler;

    public ForumQuestionController(ForumQuestionService forumQuestionService, UserService userService, UserDetail userDetail) {
        this.forumQuestionService = forumQuestionService;
        this.userService = userService;
        this.userDetail = userDetail;

    }
    @PostMapping(produces = "application/json")
    public ResponseEntity<ForumQuestionModel> userSendForumQuestion
            (@RequestParam ForumQuestionModel forumQuestionModel,
             Authentication authentication){
        logger.debug(String.format("User %s send question", authentication.getPrincipal()));
        ForumQuestion forumQuestion = new ForumQuestion();
        forumQuestion.setText(forumQuestionModel.getText());
        forumQuestion.setUser(userService.findByUserName((String) authentication.getPrincipal()));
        forumQuestionService.postForumQuestion(forumQuestion);
        return ResponseEntity.ok(forumQuestionRepresentationModelAssembler.toModel(forumQuestion));

    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<ForumQuestionModel> getForumQuestionByid(@RequestParam (name = "forumQuestionId") Long id){
       return forumQuestionService.getForumQuestionByid(id)
               .map(forumQuestionRepresentationModelAssembler::toModel)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path="{id}",produces = "application/json")
    public ResponseEntity<ForumQuestionModel> editForumQuestion(@RequestParam ForumQuestionModel forumQuestionModel,
                                                                @PathVariable long id){
        Optional<ForumQuestion> forumQuestionByid = forumQuestionService.getForumQuestionByid(id);
        if(forumQuestionByid.isEmpty()){
            throw new EntityNotFoundException();
        }
        ForumQuestion forumQuestion1 = forumQuestionByid.get();
        forumQuestion1.setText(forumQuestionModel.getText());
        forumQuestionService.updateForumQuestion(forumQuestion1);
        return forumQuestionService.getForumQuestionByid(forumQuestion1.getId())
                .map(forumQuestionRepresentationModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
