package hiperquiz.commands.quizzes;

import hiperquiz.entities.Quiz;
import hiperquiz.services.QuizService;
import hiperquiz.view.Command;
import hiperquiz.view.OutputUtils;

import java.util.List;

public class ListAllQuizzesCommand implements Command {
    private QuizService quizService;

    public ListAllQuizzesCommand(QuizService quizService) {
        this.quizService = quizService;
    }

    @Override
    public boolean execute() {
        List<Quiz> allQuizzes =  quizService.getAll();
        System.out.println(OutputUtils.printAllQuizzes(allQuizzes));
        return true;
    }
}
