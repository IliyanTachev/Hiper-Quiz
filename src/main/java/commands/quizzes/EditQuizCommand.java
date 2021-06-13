package commands.quizzes;

import services.QuizService;
import view.Command;

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
