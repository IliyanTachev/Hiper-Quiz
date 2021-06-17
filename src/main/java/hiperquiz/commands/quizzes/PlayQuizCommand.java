package hiperquiz.commands.quizzes;

import hiperquiz.exception.EntityAlreadyExistsException;
import hiperquiz.entities.Player;
import hiperquiz.entities.Quiz;
import hiperquiz.entities.QuizResult;
import hiperquiz.entities.User;
import hiperquiz.exception.EntityNotFoundException;
import hiperquiz.services.AnswerService;
import hiperquiz.services.QuizResultService;
import hiperquiz.services.QuizService;
import hiperquiz.services.UserService;
import hiperquiz.view.Command;
import hiperquiz.view.InputUtils;
import hiperquiz.view.OutputUtils;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PlayQuizCommand implements Command {
    private QuizService quizService;
    private UserService userService;
    private AnswerService answerService;
    private QuizResultService quizResultService;
    private Scanner in;

    public PlayQuizCommand(QuizService quizService, UserService userService, AnswerService answerService, QuizResultService quizResultService, Scanner in) {
        this.quizService = quizService;
        this.userService = userService;
        this.answerService = answerService;
        this.quizResultService = quizResultService;
        this.in = in;
    }

    @Override
    public boolean execute() {
        System.out.println(OutputUtils.printAllQuizzes(quizService.getAll())); // Print all Quizzes
        System.out.println("Enter Quiz ID: ");
        Long selectedQuizId = Long.parseLong(in.nextLine());
        Quiz fetchedQuiz = null;

        try{
            fetchedQuiz = quizService.getById(selectedQuizId);
        } catch(EntityNotFoundException e){
            System.err.println("Please enter valid Quiz ID.");
            return false;
        }

        int correctAnswersCounter = 0;
        int correctAnswersTotalScore = 0;
        List<Long> correctAnswersAssumptionIds = InputUtils.inputQuizGame(in, fetchedQuiz);
        for(Long answerId : correctAnswersAssumptionIds){
            int answerScore =  answerService.getById(answerId).getScore();
            if(answerScore > 0) {
                correctAnswersCounter++;
                correctAnswersTotalScore += answerScore;
            }
        }

        User loggedUser = userService.getLoggedUser();
        QuizResult result = new QuizResult(loggedUser, fetchedQuiz, correctAnswersTotalScore);
        if(loggedUser instanceof Player) ((Player) loggedUser).addResult(result);

        try {
            quizResultService.create(result);
        } catch (EntityAlreadyExistsException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("You scored %d / %d correct answers.", correctAnswersCounter, fetchedQuiz.getQuestionsSize()));
        System.out.println(OutputUtils.formatQuizResult(result));
        return true;
    }
}
