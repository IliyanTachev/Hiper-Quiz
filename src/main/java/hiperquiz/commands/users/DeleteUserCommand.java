package hiperquiz.commands.users;
import hiperquiz.view.Command;

public class DeleteUserCommand implements Command {
    public DeleteUserCommand() {}

    @Override
    public boolean execute() {
        System.exit(0);
        return true;
    }
}
