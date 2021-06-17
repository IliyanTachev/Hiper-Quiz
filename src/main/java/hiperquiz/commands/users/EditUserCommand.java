package hiperquiz.commands.users;

import hiperquiz.services.UserService;
import hiperquiz.view.Command;

public class EditUserCommand implements Command {
    private UserService userService;

    public EditUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean execute() {
        System.exit(0);
        return true;
    }
}
