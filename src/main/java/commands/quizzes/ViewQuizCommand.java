package commands.quizzes;

import services.QuizService;
import view.Command;

public class ViewQuizCommand implements Command{
    private QuizService quizService;

    public ViewQuizCommand(QuizService quizService) {
     this.quizService = quizService;
    }

    @Override
    public boolean execute() {
        return false;
    }
}
