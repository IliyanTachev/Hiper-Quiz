package util;

import dao.AnswerRepository;
import dao.QuestionRepository;
import dao.QuizRepository;
import dao.impl.*;
import exception.EntityAlreadyExistsException;
import model.*;
import services.UserService;
import services.impl.UserServiceImpl;

import java.util.Arrays;

public class InitialDataSeeder {
    private final User[] SAMPLE_USERS = {
            new User("iliqn@gmail.com", "iliqn123", "12345", Gender.MALE),
            new User("maria40@gmail.com", "maria45", "dfjkgd", Gender.FEMALE),
            new User("mitko@gmail.com", "mitko_nik", "nik_mitko-1234", Gender.MALE),
            new User("peshkoeqk", "petshko@gamil.com", "ot1do9", Gender.MALE)
    };

    private final Quiz[] SAMPLE_QUIZZES = {
            new Quiz("Presidents of the USA", SAMPLE_USERS[0], "Questions on presidents of the USA", 2, "presidents, usa"),
            new Quiz("Presidents of Bulgaria", SAMPLE_USERS[1], "Questions on presidents of Bulgaria", 4, "presidents, bulgaria"),
            new Quiz("Presidents of Russia", SAMPLE_USERS[2], "Questions on presidents of Russia", 3, "presidents, russia")
    };

    private final Question[] SAMPLE_QUESTIONS_QUIZ_1  = {
            new Question(SAMPLE_QUIZZES[0], "Who is the first president of USA?"),
            new Question(SAMPLE_QUIZZES[0], "Who is the current president of USA?")
    };

    private final Question[] SAMPLE_QUESTIONS_QUIZ_2  = {
            new Question(SAMPLE_QUIZZES[1], "Who is the first president of Bulgaria?"),
            new Question(SAMPLE_QUIZZES[1], "Who is the current president of Bulgaria?"),
            new Question(SAMPLE_QUIZZES[1], "Who was the 4th president of Bulgaria?"),
            new Question(SAMPLE_QUIZZES[1], "Who was the second president of Bulgaria?")
    };

    private final Question[] SAMPLE_QUESTIONS_QUIZ_3  = {
            new Question(SAMPLE_QUIZZES[2], "Who is the current president of Russia?"),
            new Question(SAMPLE_QUIZZES[2], "Who was the 3th president of Russia?"),
            new Question(SAMPLE_QUIZZES[2], "Who was the second president of Russia?")
    };

    private final Answer[] SAMPLE_ANSWERS_QUIZ_1_QUESTION_1= {
            new Answer(SAMPLE_QUESTIONS_QUIZ_1[0], "Donald Trump", 0),
            new Answer(SAMPLE_QUESTIONS_QUIZ_1[0], "George Washington",  1),
            new Answer(SAMPLE_QUESTIONS_QUIZ_1[0], "Joe Biden",  0)
    };

    private final Answer[] SAMPLE_ANSWERS_QUIZ_1_QUESTION_2= {
            new Answer(SAMPLE_QUESTIONS_QUIZ_1[1], "Elon Musk", 0),
            new Answer(SAMPLE_QUESTIONS_QUIZ_1[1], "Joe Rogan",  0),
            new Answer(SAMPLE_QUESTIONS_QUIZ_1[1], "Joe Biden",  1)
    };

    private final Answer[] SAMPLE_ANSWERS_QUIZ_2_QUESTION_1= {
            new Answer(SAMPLE_QUESTIONS_QUIZ_2[0], "Zhelyu Zhelev", 1),
            new Answer(SAMPLE_QUESTIONS_QUIZ_2[0], "Boyko Borissov",  0),
            new Answer(SAMPLE_QUESTIONS_QUIZ_2[0], "Joe Biden",  0)
    };

    private final Answer[] SAMPLE_ANSWERS_QUIZ_2_QUESTION_2= {
            new Answer(SAMPLE_QUESTIONS_QUIZ_2[1], "George Purvanov", 0),
            new Answer(SAMPLE_QUESTIONS_QUIZ_2[1], "Rosen Plevneliev",  0),
            new Answer(SAMPLE_QUESTIONS_QUIZ_2[1], "Rumen Radev",  1)
    };

    private final Answer[] SAMPLE_ANSWERS_QUIZ_2_QUESTION_3= {
            new Answer(SAMPLE_QUESTIONS_QUIZ_2[2], "Rosen Plevneliev", 1),
            new Answer(SAMPLE_QUESTIONS_QUIZ_2[2], "Kubrat Pulev",  0),
            new Answer(SAMPLE_QUESTIONS_QUIZ_2[2], "Zhelyu Zhelev",  0)
    };

    private final Answer[] SAMPLE_ANSWERS_QUIZ_2_QUESTION_4= {
            new Answer(SAMPLE_QUESTIONS_QUIZ_2[3], "Rumen Radev", 0),
            new Answer(SAMPLE_QUESTIONS_QUIZ_2[3], "Geroge Purvanov",  0),
            new Answer(SAMPLE_QUESTIONS_QUIZ_2[3], "Petar Stoyanov",  1)
    };

    private final Answer[] SAMPLE_ANSWERS_QUIZ_3_QUESTION_1= {
            new Answer(SAMPLE_QUESTIONS_QUIZ_3[0], "George Purvanov", 0),
            new Answer(SAMPLE_QUESTIONS_QUIZ_3[0], "Vladimir Moronov",  0),
            new Answer(SAMPLE_QUESTIONS_QUIZ_3[0], "Vladimir Putin",  1)
    };

    private final Answer[] SAMPLE_ANSWERS_QUIZ_3_QUESTION_2= {
            new Answer(SAMPLE_QUESTIONS_QUIZ_3[1], "Boris Yeltsin", 0),
            new Answer(SAMPLE_QUESTIONS_QUIZ_3[1], "Vladimir Putin",  0),
            new Answer(SAMPLE_QUESTIONS_QUIZ_3[1], "Dmitry Medvedev",  1)
    };

    private final Answer[] SAMPLE_ANSWERS_QUIZ_3_QUESTION_3= {
            new Answer(SAMPLE_QUESTIONS_QUIZ_3[2], "Vladimir Putin", 1),
            new Answer(SAMPLE_QUESTIONS_QUIZ_3[2], "mitry Medvedev",  0),
            new Answer(SAMPLE_QUESTIONS_QUIZ_3[2], "Boris Yeltsin",  0)
    };

    private final QuizResult[] SAMPLE_QUIZ_RESULTS = {
            new QuizResult(SAMPLE_USERS[0], SAMPLE_QUIZZES[0], 1),
            new QuizResult(SAMPLE_USERS[0],SAMPLE_QUIZZES[2], 2),
            new QuizResult(SAMPLE_USERS[1], SAMPLE_QUIZZES[1], 3),
            new QuizResult(SAMPLE_USERS[2], SAMPLE_QUIZZES[2], 1),
            new QuizResult(SAMPLE_USERS[3], SAMPLE_QUIZZES[0], 2)
    };

    public void seedData(){
        // Set Questions for every Quiz
        SAMPLE_QUIZZES[0].setQuestions(Arrays.asList(SAMPLE_QUESTIONS_QUIZ_1)); // Quiz about USA presidents
        SAMPLE_QUIZZES[1].setQuestions(Arrays.asList(SAMPLE_QUESTIONS_QUIZ_2)); // Quiz about Bulgarian presidents
        SAMPLE_QUIZZES[2].setQuestions(Arrays.asList(SAMPLE_QUESTIONS_QUIZ_3)); // Quiz about Russian presidents

        // Set Quizzes fro every User
        SAMPLE_USERS[0].addQuiz(SAMPLE_QUIZZES[0]);
        SAMPLE_USERS[1].addQuiz(SAMPLE_QUIZZES[1]);
        SAMPLE_USERS[2].addQuiz(SAMPLE_QUIZZES[2]);

        // <-- Saving entities (Persistence) -->
        // Saving users
        UserService userService = new UserServiceImpl(new UserRepositoryInMemoryImpl(new LongKeyGenerator()));
        Arrays.asList(SAMPLE_USERS).stream().forEach(u -> {
            try {
                userService.create(u);
            } catch (EntityAlreadyExistsException e) {
                e.printStackTrace();
            }
        });

        // Saving quizzes
        QuizRepository quizRepository = new QuizRepositoryInMemoryImpl(new LongKeyGenerator());
        Arrays.asList(SAMPLE_QUIZZES).stream().forEach(quiz -> {
            try {
                quizRepository.create(quiz);
            } catch (EntityAlreadyExistsException e) {
                e.printStackTrace();
            }
        });

        // Saving questions
        QuestionRepository questionRepository = new QuestionRepositoryInMemoryImpl(new LongKeyGenerator());
        for(Quiz quiz : SAMPLE_QUIZZES){
            for(Question question : quiz.getQuestions()){
                try {
                    questionRepository.create(question);
                } catch (EntityAlreadyExistsException e) {
                    e.printStackTrace();
                }
            }
        }

        // Saving answers
        AnswerRepository answerRepository = new AnswerRepositoryInMemoryImpl(new LongKeyGenerator());
        for(Quiz quiz : SAMPLE_QUIZZES){
            for(Question question : quiz.getQuestions()){
                for(Answer answer : question.getAnswers()) {
                    try {
                        answerRepository.create(answer);
                    } catch (EntityAlreadyExistsException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
