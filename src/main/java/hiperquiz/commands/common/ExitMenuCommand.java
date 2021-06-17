package hiperquiz.commands.common;

import hiperquiz.dao.*;
import hiperquiz.services.*;
import hiperquiz.view.Command;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ExitMenuCommand implements Command {
    private final String SAVE_ENTITIES_FILE = "hiperQuiz.db";
    private UserRepository userRepository;
    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private QuizResultRepository quizResultRepository;

    public ExitMenuCommand(UserService userService, QuizService quizService, QuestionService questionService, AnswerService answerService, QuizResultService quizResultService) {
        this.userRepository  = (UserRepository) userService.getRepository();
        this.quizRepository  = (QuizRepository) quizService.getRepository();
        this.questionRepository  = (QuestionRepository) questionService.getRepository();
        this.answerRepository  = (AnswerRepository) answerService.getRepository();
        this.quizResultRepository  = (QuizResultRepository) quizResultService.getRepository();
    }

    @Override
        public boolean execute() {
            SaveEntitiesCommand saveCommand = null;
            try {
                saveCommand =
                        new SaveEntitiesCommand
                                (
                                        new FileOutputStream(SAVE_ENTITIES_FILE),
                                        userRepository,
                                        quizRepository,
                                        questionRepository,
                                        answerRepository,
                                        quizResultRepository
                                );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            saveCommand.execute();
            System.exit(0);
            return true;
        }
}
