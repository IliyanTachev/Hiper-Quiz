package util;

import exception.EntityAlreadyExistsException;
import model.*;
import services.AnswerService;
import services.QuestionService;
import services.QuizService;
import services.UserService;

import java.util.Arrays;

public class InitialDataSeeder {
    private final User[] SAMPLE_USERS = {
            new User("iliqn@gmail.com", "iliqn123", "abcmnF$123", Gender.MALE),
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
            new QuizResult(SAMPLE_USERS[2], SAMPLE_QUIZZES[2], 1)
    };

    private UserService userService;
    private QuizService quizService;
    private QuestionService questionService;
    private AnswerService answerService;

    public InitialDataSeeder(UserService userService, QuizService quizService, QuestionService questionService, AnswerService answerService) {
        this.userService = userService;
        this.quizService = quizService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    public void seedData(){
        // Set Answers for every Question
        SAMPLE_QUESTIONS_QUIZ_1[0].setAnswers(Arrays.asList(SAMPLE_ANSWERS_QUIZ_1_QUESTION_1)); // Quiz 1 -> Question 1
        SAMPLE_QUESTIONS_QUIZ_1[1].setAnswers(Arrays.asList(SAMPLE_ANSWERS_QUIZ_1_QUESTION_2)); // Quiz 1 -> Question 2
        SAMPLE_QUESTIONS_QUIZ_2[0].setAnswers(Arrays.asList(SAMPLE_ANSWERS_QUIZ_2_QUESTION_1)); // Quiz 2 -> Question 1
        SAMPLE_QUESTIONS_QUIZ_2[1].setAnswers(Arrays.asList(SAMPLE_ANSWERS_QUIZ_2_QUESTION_2)); // Quiz 2 -> Question 2
        SAMPLE_QUESTIONS_QUIZ_2[2].setAnswers(Arrays.asList(SAMPLE_ANSWERS_QUIZ_2_QUESTION_3)); // Quiz 2 -> Question 3
        SAMPLE_QUESTIONS_QUIZ_2[3].setAnswers(Arrays.asList(SAMPLE_ANSWERS_QUIZ_2_QUESTION_4)); // Quiz 2 -> Question 4
        SAMPLE_QUESTIONS_QUIZ_3[0].setAnswers(Arrays.asList(SAMPLE_ANSWERS_QUIZ_3_QUESTION_1)); // Quiz 3 -> Question 1
        SAMPLE_QUESTIONS_QUIZ_3[1].setAnswers(Arrays.asList(SAMPLE_ANSWERS_QUIZ_3_QUESTION_2)); // Quiz 3 -> Question 2
        SAMPLE_QUESTIONS_QUIZ_3[2].setAnswers(Arrays.asList(SAMPLE_ANSWERS_QUIZ_3_QUESTION_3)); // Quiz 3 -> Question 3

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
        Arrays.asList(SAMPLE_USERS).stream().forEach(u -> {
            try {
                userService.create(u);
            } catch (EntityAlreadyExistsException e) {
                e.printStackTrace();
            }
        });

        // Saving quizzes
        Arrays.asList(SAMPLE_QUIZZES).stream().forEach(quiz -> {
            try {
                quizService.create(quiz);
            } catch (EntityAlreadyExistsException e) {
                e.printStackTrace();
            }
        });

        // Saving questions
        for(Quiz quiz : SAMPLE_QUIZZES){
            for(Question question : quiz.getQuestions()){
                try {
                    questionService.create(question);
                } catch (EntityAlreadyExistsException e) {
                    e.printStackTrace();
                }
            }
        }

        // Saving answers
        for(Quiz quiz : SAMPLE_QUIZZES){
            for(Question question : quiz.getQuestions()){
                for(Answer answer : question.getAnswers()) {
                    try {
                        answerService.create(answer);
                    } catch (EntityAlreadyExistsException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
