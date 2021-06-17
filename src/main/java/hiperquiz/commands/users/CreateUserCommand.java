package hiperquiz.commands.users;

import hiperquiz.exception.EntityAlreadyExistsException;
import hiperquiz.entities.User;
import hiperquiz.services.UserService;
import hiperquiz.view.Command;
import hiperquiz.view.InputUtils;

import java.util.Scanner;

public class CreateUserCommand implements Command{
    private UserService userService;
    private Scanner in;

    public CreateUserCommand(UserService userService, Scanner in) {
        this.userService = userService;
        this.in = in;
    }

    @Override
    public boolean execute() {
        User user = InputUtils.inputUser(in);
        try {
            userService.create(user);
            System.out.println("User was created successfully.");
            return true;
        } catch (EntityAlreadyExistsException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
