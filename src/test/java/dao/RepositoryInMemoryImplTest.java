package dao;

import dao.impl.LongKeyGenerator;
import exception.EntityAlreadyExistsException;
import exception.EntityNotFoundException;
import model.Gender;
import model.Identifiable;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class RepositoryInMemoryImplTest {
    private static final List<User> SAMPLE_ENTITIES = List.of(
            new User("iliqn1@gmail.com", "iliqn1", "12345FGd34", Gender.MALE),
            new User("iliqn2@gmail.com", "iliqn2", "1$$45FGd34", Gender.MALE),
            new User("iliqn3@gmail.com", "iliqn3", "551245$FG4", Gender.MALE),
            new User("iliqn4@gmail.com", "iliqn4", "12345FGd34", Gender.MALE),
            new User("iliqn5@gmail.com", "iliqn5", "F$345FGd34", Gender.MALE)
    );

    private static final User SAMPLE_NEW_USER =  new User("iliqn10@gmail.com", "iliqno10", "1GHJ3$d34", Gender.MALE);

    private KeyGenerator<Long> keyGenerator;
    private Repository repo;
    private final Long ENTITY_ID = 1L;
    private User user;

    @BeforeEach
    void setUp() {
        // Before test case
        keyGenerator = new LongKeyGenerator();
        repo = new RepositoryInMemoryImpl<Long, User>(keyGenerator);
    }

    @Test
    @DisplayName("List all entities")
    void findAll() {
        fillInEntities();
        List<User> users = repo.findAll();
        assertNotNull(users, "Entities result is null"); // test
        assertEquals(SAMPLE_ENTITIES.size(), users.size(), "Incorrect entities size");
    }

    @Test
    @DisplayName("Find entity by Id")
    void findById() {
        fillInEntities();
        Optional<User> user = repo.findById(ENTITY_ID);
        assertTrue(user.isPresent(), "Entity is null");
        assertEquals(user.get().getId(), ENTITY_ID, "ID is incorrect");
        assertEquals(user.get().getEmail(), SAMPLE_ENTITIES.get((int) (ENTITY_ID-1)).getEmail(), "Email is incorrect");
        assertEquals(user.get().getUsername(), SAMPLE_ENTITIES.get((int) (ENTITY_ID-1)).getUsername(), "Username is incorrect");
        assertEquals(user.get().getPassword(), SAMPLE_ENTITIES.get((int) (ENTITY_ID-1)).getPassword(), "Password is incorrect");
        assertEquals(user.get().getGender(), SAMPLE_ENTITIES.get((int)(ENTITY_ID-1)).getGender(), "Gender is incorrect");
    }

    @Test
    @DisplayName("Create new entity with No Exception")
    void createEmptyRepo() {
        assertDoesNotThrow(() -> user = (User) repo.create(SAMPLE_NEW_USER), "create method throws exception");
        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals(user.getEmail(), SAMPLE_NEW_USER.getEmail());
        assumingThat(
                keyGenerator.getClass().getSimpleName().equals("LongKeyGenerator"),
                () -> assertEquals(ENTITY_ID, user.getId(), "Repo with LongKey Generator should return first ID = 1")
        );
    }

    @Test
    @DisplayName("Create new entity with Exception (AlreadyExists)")
    void createNotEmptyRepo() {
        fillInEntities();
        assertThrows(EntityAlreadyExistsException.class, () -> repo.create(SAMPLE_ENTITIES.get(0)), "create method does not throw exception");
    }

    @Test
    @DisplayName("List all entities")
    void update() {
    }

    @Test
    @DisplayName("Delete entity by Id with Exception (EntityNotFoundException)")
    void deleteByIdWithEmptyRepo() {
        assertThrows(EntityNotFoundException.class, () -> repo.deleteById(ENTITY_ID), "deleteById method does not throw exception");
    }

    @Test
    @DisplayName("Delete entity by Id with No Exception")
    void deleteByIdWithNoEmptyRepo() {
        fillInEntities();
        assertDoesNotThrow(() -> user = (User) repo.deleteById(ENTITY_ID), "deleteById method throws exception");
        assertEquals(user.getEmail(), SAMPLE_ENTITIES.get((int) (ENTITY_ID-1)).getEmail(), "Incorrect User has been returned");
        assertEquals(repo.findAll().size(), SAMPLE_ENTITIES.size()-1, "Incorrect size");
        Optional<User> userFound = repo.findById(ENTITY_ID);
        assertTrue(userFound.isEmpty(), "User is not deleted");
    }

    @Test
    @DisplayName("List all entities")
    void count() {
        fillInEntities();
        assertEquals(repo.count(), SAMPLE_ENTITIES.size(), "Invalid size");
    }

    private void fillInEntities(){
        SAMPLE_ENTITIES.forEach(p -> {
            try {
                repo.create(p);
            } catch (EntityAlreadyExistsException e) {
//                log.error("Error adding products to repository", e);
            }
        });
    }
}