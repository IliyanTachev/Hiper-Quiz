package hiperquiz.commands.quizzes;

import hiperquiz.services.QuizService;
import hiperquiz.view.Command;

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
