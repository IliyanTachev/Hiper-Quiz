package controller;

import com.sun.tools.javac.Main;
import dao.AnswerRepository;
import dao.QuestionRepository;
import dao.QuizRepository;
import dao.UserRepository;
import dao.impl.*;
import exception.EntityAlreadyExistsException;
import exception.EntityNotFoundException;
import model.*;
import services.*;
import services.impl.*;
import util.InitialDataSeeder;
import view.Command;
import view.MainMenu;
public class Controller {

    public static void main(String[] args) throws EntityAlreadyExistsException {
        UserService userService = new UserServiceImpl(new UserRepositoryInMemoryImpl(new LongKeyGenerator()));
        QuizService quizService = new QuizServiceImpl(new QuizRepositoryInMemoryImpl(new LongKeyGenerator()));
        QuestionService questionService = new QuestionServiceImpl(new QuestionRepositoryInMemoryImpl(new LongKeyGenerator()));
        AnswerService answerService = new AnswerServiceImpl(new AnswerRepositoryInMemoryImpl(new LongKeyGenerator()));
        QuizResultService quizResultService = new QuizResultServiceImpl(new QuizResultRepositoryInMemoryImpl(new LongKeyGenerator()));

//        InitialDataSeeder dataSeeder = new InitialDataSeeder(userService, quizService, questionService, answerService);
//        dataSeeder.seedData();

        CommandRegister commandRegister = new CommandRegister(userService, quizService, questionService, answerService, quizResultService);
        MainMenu mainMenu = new MainMenu(commandRegister, System.in);
        mainMenu.start();
    }
}
