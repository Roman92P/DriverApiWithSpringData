package com.pashkov.driverapi.app.resource;

import com.pashkov.driverapi.app.DTOs.ForumQuestionDTO;
import com.pashkov.driverapi.app.model.ForumQuestion;
import com.pashkov.driverapi.app.model.ForumQuestionModel;
import com.pashkov.driverapi.app.model.ForumQuestionRepresentationModelAssembler;
import com.pashkov.driverapi.app.model.UserDetail;
import com.pashkov.driverapi.app.service.ForumQuestionService;
import com.pashkov.driverapi.app.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/forumQuestions")
public class ForumQuestionController {

    private final Logger logger = LoggerFactory.getLogger(ForumQuestionController.class);

    private final ForumQuestionService forumQuestionService;

    private final UserService userService;

    private final UserDetail userDetail;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ForumQuestionRepresentationModelAssembler forumQuestionRepresentationModelAssembler;

    public ForumQuestionController(ForumQuestionService forumQuestionService, UserService userService, UserDetail userDetail) {
        this.forumQuestionService = forumQuestionService;
        this.userService = userService;
        this.userDetail = userDetail;

    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ForumQuestionModel> userSendForumQuestion
            (@RequestBody ForumQuestionDTO forumQuestionDTO,
             Authentication authentication) {
        ForumQuestion forumQuestion = convertToEntity(forumQuestionDTO);
        forumQuestion.setUser(userService.findByUserName((String) authentication.getPrincipal()));
        forumQuestionService.postForumQuestion(forumQuestion);
        return ResponseEntity.ok(forumQuestionRepresentationModelAssembler.toModel(forumQuestion));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ForumQuestionModel> getForumQuestionByid(@PathVariable Long id) {
        return forumQuestionService.getForumQuestionByid(id)
                .map(forumQuestionRepresentationModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CollectionModel<ForumQuestionModel>> getAllForumQuestions() {
        Set<ForumQuestion> allForumQuestions = forumQuestionService.getAllForumQuestions();
        return new ResponseEntity<>(forumQuestionRepresentationModelAssembler.toCollectionModel(allForumQuestions),HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ForumQuestionModel> editForumQuestion(@RequestBody ForumQuestionModel forumQuestionModel,
                                                                @PathVariable long id) {
        Optional<ForumQuestion> forumQuestionByid = forumQuestionService.getForumQuestionByid(id);
        if (forumQuestionByid.isEmpty()) {
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

    private ForumQuestion convertToEntity(ForumQuestionDTO forumQuestionDTO) {
        ForumQuestion mapped = modelMapper.map(forumQuestionDTO, ForumQuestion.class);
        mapped.setText(forumQuestionDTO.getQuestionText());
        return mapped;
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteForumQuestion(@PathVariable long id) {
        forumQuestionService.deleteForumQuestion(id);
        return ResponseEntity.ok().build();
    }
}
