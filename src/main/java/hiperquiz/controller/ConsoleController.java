package hiperquiz.controller;

import hiperquiz.dao.UserRepository;
import hiperquiz.dao.impl.*;
import hiperquiz.exception.EntityAlreadyExistsException;
import hiperquiz.services.*;
import hiperquiz.services.impl.*;
import hiperquiz.view.MainMenu;

public class ConsoleController {

    public static void main(String[] args) throws EntityAlreadyExistsException {
        UserRepository userJPARepository = new UserRepositoryJPAImpl();
        ((UserRepositoryJPAImpl)userJPARepository).init();
        UserService userService = new UserServiceImpl(userJPARepository);
        QuizService quizService = new QuizServiceImpl(new QuizRepositoryInMemoryImpl(new LongKeyGenerator(0)));
        QuestionService questionService = new QuestionServiceImpl(new QuestionRepositoryInMemoryImpl(new LongKeyGenerator(0)));
        AnswerService answerService = new AnswerServiceImpl(new AnswerRepositoryInMemoryImpl(new LongKeyGenerator(0)));
        QuizResultService quizResultService = new QuizResultServiceImpl(new QuizResultRepositoryInMemoryImpl(new LongKeyGenerator(0)));

//        InitialDataSeeder dataSeeder = new InitialDataSeeder(userService, quizService, questionService, answerService);
//        dataSeeder.seedData();

        MainMenu mainMenu = new MainMenu(System.in, userService, quizService, questionService, answerService, quizResultService);
        mainMenu.start();
    }
}
