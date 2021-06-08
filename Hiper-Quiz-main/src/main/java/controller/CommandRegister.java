package controller;

import dao.AnswerRepository;
import dao.QuestionRepository;
import dao.QuizRepository;
import dao.UserRepository;
import exception.EntityAlreadyExistsException;
import exception.EntityNotFoundException;
import model.*;
import services.*;

import java.util.List;
import java.util.Optional;

public class CommandRegister {
    private UserService userService;
    private QuizService quizService;
    private QuestionService questionService;
    private AnswerService answerService;
    private QuizResultService quizResultService;

    public CommandRegister(UserService userService, QuizService quizService, QuestionService questionService, AnswerService answerService, QuizResultService quizResultService) {
        this.userService = userService;
        this.quizService = quizService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.quizResultService = quizResultService;
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

    public User registerUser(User user) throws EntityAlreadyExistsException, EntityNotFoundException {
       User registeredUser = createUser(user); // registeredUser
       return userService.login(new LoginUser(registeredUser.getUsername(), registeredUser.getPassword()));
    }

    public User loginUser(LoginUser loginUser) throws EntityNotFoundException {
        return userService.login(loginUser);
    }

    public User logoutUser(){
        return userService.logout();
    }

    public User getLoggedUser(){
        return userService.getLoggedUser();
    }

    public Quiz createQuiz(Quiz quiz) throws EntityAlreadyExistsException {
        if(!quiz.getQuestions().isEmpty()) quiz.getQuestions().forEach(question -> {
            question.getAnswers().forEach(answer -> {
                try {
                    answerService.create(answer);
                } catch (EntityAlreadyExistsException e) {
//                    e.printStackTrace();
                    System.err.println("Questions and Answers for this Quiz failed to be saved.");
                }
            });

            try {
                questionService.create(question);
            } catch (EntityAlreadyExistsException e) {
//                e.printStackTrace();
                System.err.println("Questions and Answers for this Quiz failed to be saved.");
            }
        });
        return quizService.create(quiz);
    }

    public List<Quiz> getAllQuizzes(){
        return quizService.getAll();
    }

    public Optional<Quiz> getQuizById(Long id){
        return quizService.getById(id);
    }

    public Optional<Answer> getAnswerById(Long id){
        return answerService.getById(id);
    }

    public QuizResult createQuizResult(QuizResult quizResult) throws EntityAlreadyExistsException {
        return quizResultService.create(quizResult);
    }

    public List<QuizResult> getAllQuizResults(){
        return quizResultService.getAll();
    }

    public UserRepository getUserRepo(){
        return (UserRepository) userService.getRepo();
    }

    public QuizRepository getQuizRepo(){
        return (QuizRepository) quizService.getRepo();
    }

    public QuestionRepository getQuestionRepo(){
        return (QuestionRepository) questionService.getRepo();
    }

    public AnswerRepository getAnswerRepo(){
        return (AnswerRepository) answerService.getRepo();
    }
}
