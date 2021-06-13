package commands.quizzes;

import model.QuizResult;
import services.QuizResultService;
import view.Command;
import view.OutputUtils;

import java.util.List;

public class ListAllQuizResultsCommand implements Command {
    private QuizResultService quizResultService;

    public ListAllQuizResultsCommand(QuizResultService quizResultService) {
        this.quizResultService = quizResultService;
    }

    @Override
    public boolean execute() {
        List<QuizResult> quizResults = quizResultService.getAll();
        System.out.println(OutputUtils.formatAllQuizResults(quizResults));
        return true;
    }
}
