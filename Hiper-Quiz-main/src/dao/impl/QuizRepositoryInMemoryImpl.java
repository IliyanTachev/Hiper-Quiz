package dao.impl;

import dao.QuizRepository;
import dao.RepositoryInMemoryImpl;
import model.Quiz;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class QuizRepositoryInMemoryImpl extends RepositoryInMemoryImpl<Long, Quiz> implements QuizRepository{

    @Override
    public Optional<Quiz> findByTitle(String title) {
        return findAll().stream().filter(p -> p.getTitle().equals(title)).findFirst();
    }

    @Override
    public Optional<Quiz> findByDescription(String description) {
        return findAll().stream().filter(p -> p.getDescription().equals(description)).findFirst();
    }

    @Override
    public Optional<Quiz> findByTags(String tags) {
        return findAll().stream().filter(p -> p.getTags().equals(tags)).findFirst();
    }

    @Override
    public List<Quiz> findByExpectedDuration(int expectedDuration) {
        return findAll().stream().filter(p -> p.getExpectedDuration() == expectedDuration).collect(Collectors.toList());
    }
}
