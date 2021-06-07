package dao.impl;

import dao.KeyGenerator;
import dao.QuizRepository;
import dao.RepositoryInMemoryImpl;
import model.Quiz;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class QuizRepositoryInMemoryImpl extends RepositoryInMemoryImpl<Long, Quiz> implements QuizRepository{

    public QuizRepositoryInMemoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }

    @Override
    public Set<Quiz> findByTitle(String title) {
        return findAll().stream().filter(p -> p.getTitle().equals(title)).collect(Collectors.toSet());
    }

    @Override
    public Set<Quiz> findByDescription(String description) {
        return findAll().stream().filter(p -> p.getDescription().equals(description)).collect(Collectors.toSet());
    }

    @Override
    public Set<Quiz> findByTags(String tags) {
        return findAll().stream().filter(p -> p.getTags().equals(tags)).collect(Collectors.toSet());
    }

    @Override
    public Set<Quiz> findByExpectedDuration(int expectedDuration) {
        return findAll().stream().filter(p -> p.getExpectedDuration() == expectedDuration).collect(Collectors.toSet());
    }

    @Override
    public Set<Quiz> findByAllCriterias(String title, String description, String tags, int expectedDuration) {
        return findAll().stream()
                .filter(p -> p.getTitle().equals(title))
                .filter(p -> p.getDescription().equals(description))
                .filter(p -> p.getTags().equals(tags))
                .filter(p -> p.getExpectedDuration() == expectedDuration)
                .collect(Collectors.toSet());
    }
}
