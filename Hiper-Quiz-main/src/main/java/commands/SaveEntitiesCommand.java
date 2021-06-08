package commands;

import dao.AnswerRepository;
import dao.QuestionRepository;
import dao.QuizRepository;
import dao.UserRepository;
import model.AllCollections;
import view.Command;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class SaveEntitiesCommand implements Command {
    private UserRepository userRepository;
    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private OutputStream out;

    public SaveEntitiesCommand(OutputStream out, UserRepository userRepository, QuizRepository quizRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.out = out;
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public boolean execute() {
        AllCollections allCollections = new AllCollections(
                userRepository.findAll(),
                quizRepository.findAll(),
                questionRepository.findAll(),
                answerRepository.findAll());
        try(ObjectOutputStream oos = new ObjectOutputStream(out)){
            oos.writeObject(allCollections);
            System.err.println("All collections saved successfully");
            return true;
        } catch (IOException e) {
//            log.error("Error writing entities to file", e);
            e.printStackTrace();
            System.err.println("Error writing collections to file");
            return false;
        }
    }
}
