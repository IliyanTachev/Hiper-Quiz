package hiperquiz.commands.common;

import hiperquiz.dao.impl.LongKeyGenerator;
import hiperquiz.exception.EntityAlreadyExistsException;
import hiperquiz.exception.EntityCreationException;
import hiperquiz.dao.*;
import hiperquiz.entities.*;
import hiperquiz.view.Command;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class LoadEntitiesCommand implements Command {
    private UserRepository userRepository;
    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private QuizResultRepository quizResultRepository;
    private InputStream in;

    public LoadEntitiesCommand(InputStream in,
                               UserRepository userRepository,
                               QuizRepository quizRepository,
                               QuestionRepository questionRepository,
                               AnswerRepository answerRepository,
                               QuizResultRepository quizResultRepository
    ) {
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.quizResultRepository = quizResultRepository;
        this.in = in;
    }
    @Override
    public boolean execute() {
        List<User> users;
        List<Quiz> quizzes;
        List<Question> questions;
        List<Answer> answers;
        List<QuizResult> quizResults;

        try(ObjectInputStream ois = new ObjectInputStream(in)){
            AllCollections allCollections = (AllCollections) ois.readObject();
            users = allCollections.getUsers();
            quizzes = allCollections.getQuizzes();
            questions = allCollections.getQuestions();
            answers = allCollections.getAnswers();
            quizResults = allCollections.getQuizResults();

            userRepository.createBatch(users);
            quizRepository.createBatch(quizzes);
            questionRepository.createBatch(questions);
            answerRepository.createBatch(answers);
            quizResultRepository.createBatch(quizResults);

            userRepository.setKeyGenerator(new LongKeyGenerator(users.size()));
            quizRepository.setKeyGenerator(new LongKeyGenerator(quizzes.size()));
            questionRepository.setKeyGenerator(new LongKeyGenerator(questions.size()));
            answerRepository.setKeyGenerator(new LongKeyGenerator(answers.size()));
            quizResultRepository.setKeyGenerator(new LongKeyGenerator(quizResults.size()));

            System.out.println("All collections loaded successfully");

        } catch (IOException | ClassNotFoundException e) {
//            log.error("Error reading collections from file", e);
            System.err.println("Error reading collections from file");
            return false;
        } catch (EntityAlreadyExistsException e) {
//            log.error("Error adding entities to repository", e);
//            return "Error adding entities to repository";
            return false;
        } catch (EntityCreationException e) {
            System.err.println("Error creating entity");
            e.printStackTrace();
        }
        return true;
    }
}
