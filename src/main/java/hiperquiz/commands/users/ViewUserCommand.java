package hiperquiz.commands.users;

import hiperquiz.entities.User;
import hiperquiz.services.UserService;
import hiperquiz.view.Command;
import hiperquiz.view.OutputUtils;

import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ViewUserCommand implements Command {
    private UserService userService;
    private Scanner in;

    public ViewUserCommand(UserService userService, Scanner in) {
        this.userService = userService;
        this.in = in;
    }

    @Override
    public boolean execute() {
        System.out.println("Enter User ID: ");
        Optional<User> user = userService.read(in.nextLong());
        if(user.isPresent()) {
            OutputUtils.printUser(user.get());
            return true;
        } else {
            System.out.println("No user with such ID number was found.");
            // Wait for a little bit before continue
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
