package commands.users;

import model.User;
import services.UserService;
import view.Command;
import view.OutputUtils;

import java.util.List;

public class ListAllUsersCommand implements Command {
    private UserService userService;

    public ListAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean execute() {
        List<User> allUsers =  userService.getAll();
        System.out.println(OutputUtils.printAllUsers(allUsers));
        return true;
    }
}
