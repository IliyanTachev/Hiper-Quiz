package controller;

import exception.EntityAlreadyExistsException;
import model.User;
import services.AnswerService;
import services.QuestionService;
import services.QuizService;
import services.UserService;

import java.util.List;
import java.util.Optional;

public class CommandRegister {
    private UserService userService;
    private QuizService quizService;
    private QuestionService questionService;
    private AnswerService answerService;

    public CommandRegister(UserService userService, QuizService quizService, QuestionService questionService, AnswerService answerService) {
        this.userService = userService;
        this.quizService = quizService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    public User createUser(User user) throws EntityAlreadyExistsException {
        return userService.create(user);
    }

    public List<User> getAllUsers(){
        return userService.getAll();
    }

    public Optional<User> getUserById(Long id){
        return userService.read(id);
    }
}
