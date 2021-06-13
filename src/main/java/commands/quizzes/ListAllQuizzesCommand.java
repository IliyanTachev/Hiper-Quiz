package commands.quizzes;

import model.Quiz;
import services.QuizService;
import view.Command;
import view.OutputUtils;

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
