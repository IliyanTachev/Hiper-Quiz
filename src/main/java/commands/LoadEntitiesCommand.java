package commands;

import dao.*;
import exception.EntityAlreadyExistsException;
import model.AllCollections;
import view.Command;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class LoadEntitiesCommand implements Command {
    private UserRepository userRepository;
    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private InputStream in;

    public LoadEntitiesCommand(InputStream in,
                               UserRepository userRepository,
                               QuizRepository quizRepository,
                               QuestionRepository questionRepository,
                               AnswerRepository answerRepository
    ) {
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.in = in;
    }
    @Override
    public boolean execute() {
        try(ObjectInputStream ois = new ObjectInputStream(in)){
            AllCollections allCollections = (AllCollections) ois.readObject();
            userRepository.createBatch(allCollections.getUsers());
            quizRepository.createBatch(allCollections.getQuizzes());
            questionRepository.createBatch(allCollections.getQuestions());
            answerRepository.createBatch(allCollections.getAnswers());
            System.out.println("All collections loaded successfully");
        } catch (IOException | ClassNotFoundException e) {
//            log.error("Error reading collections from file", e);
            System.err.println("Error reading collections from file");
            return false;
        } catch (EntityAlreadyExistsException e) {
//            log.error("Error adding entities to repository", e);
//            return "Error adding entities to repository";
            return false;
        }
        return true;
    }
}
