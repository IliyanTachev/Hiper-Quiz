package dao;
import exception.EntityAlreadyExistsException;
import exception.NoAuthorFoundException;
import model.Quiz;
import model.User;

public interface UserRepository extends Repository<Long, User>{
    Quiz createQuiz(Quiz quiz) throws NoAuthorFoundException, EntityAlreadyExistsException;
}
