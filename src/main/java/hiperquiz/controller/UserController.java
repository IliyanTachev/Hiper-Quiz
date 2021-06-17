package hiperquiz.controller;

import hiperquiz.entities.User;
import hiperquiz.exception.InvalidEntityDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import hiperquiz.services.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/users", produces = APPLICATION_JSON_VALUE)
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User created = userService.create(user);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
                        .buildAndExpand(created.getId()).toUri())
                .body(created);
    }

    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User user){
        if (!id.equals(user.getId())) {
            throw new InvalidEntityDataException(
                    String.format("ID in URL:'%s' is different from ID in request body ID:'%s'.",
                            id, user.getId())
            );
        }

        return userService.update(user);
    }

    @DeleteMapping(path = "/{id}")
    public User deleteUser(@PathVariable Long id){
        return userService.delete(id);
    }
}
