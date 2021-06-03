package dao.impl;

import dao.QuizRepository;
import dao.RepositoryInMemoryImpl;
import dao.UserRepository;
import exception.EntityAlreadyExistsException;
import exception.EntityNotFoundException;
import exception.NoAuthorFoundException;
import model.Quiz;
import model.User;

public class UserRepositoryInMemoryImpl extends RepositoryInMemoryImpl<Long, User> implements UserRepository {
    @Override
    public Quiz createQuiz(Quiz quiz) throws NoAuthorFoundException, EntityAlreadyExistsException {
        QuizRepository quizRepository = new QuizRepositoryInMemoryImpl();
        quizRepository.create(quiz);

        User author = quiz.getAuthor();
        author.getQuizzes().add(quiz);

        try {
            update(author);
        } catch (EntityNotFoundException e) {
            throw new NoAuthorFoundException(String.format("Quiz with id='%s' was provided with invalid Auhtor with id='%s'", quiz.getId(), author.getId()));
        }

        return null;
    }
}
