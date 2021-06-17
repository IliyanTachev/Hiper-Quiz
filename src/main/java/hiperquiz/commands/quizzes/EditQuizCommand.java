package hiperquiz.commands.quizzes;

import hiperquiz.services.QuizService;
import hiperquiz.view.Command;

public class EditQuizCommand implements Command {
    private QuizService quizService;

    public EditQuizCommand(QuizService quizService) {
        this.quizService = quizService;
    }

    @Override
    public boolean execute() {
        return false;
    }
}
