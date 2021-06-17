package hiperquiz.commands.quizzes;

import hiperquiz.exception.EntityAlreadyExistsException;
import hiperquiz.entities.Quiz;
import hiperquiz.services.AnswerService;
import hiperquiz.services.QuestionService;
import hiperquiz.services.QuizService;
import hiperquiz.services.UserService;
import hiperquiz.view.Command;
import hiperquiz.view.InputUtils;

import java.util.Scanner;

public class CreateQuizCommand implements Command {
    private QuizService quizService;
    private UserService userService;
    private QuestionService questionService;
    private AnswerService answerService;
    private Scanner in;

    public CreateQuizCommand(QuizService quizService, UserService userService, QuestionService questionService, AnswerService answerService, Scanner in) {
        this.quizService = quizService;
        this.userService = userService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.in = in;
    }

    @Override
    public boolean execute() {
        Quiz quiz = InputUtils.inputQuiz(in);
        quiz.setAuthor(userService.getLoggedUser());
        try {
            Quiz createdQuiz = createQuiz(quiz);
            System.out.println("Quiz(\"" + createdQuiz.getTitle() + "\") was successfully created.");
        } catch (EntityAlreadyExistsException e) {
//                    e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return true;
    }

    private Quiz createQuiz(Quiz quiz) throws EntityAlreadyExistsException{
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
}
