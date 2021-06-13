package commands.users;

import services.UserService;
import view.Command;
import view.MainMenu;
import view.MenuItemStrings;

public class LogoutUserCommand implements Command {
    private UserService userService;

    public LogoutUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean execute() {
        if(userService.logout() == null){
            MainMenu.loadMenuItems(MenuItemStrings.menuItemStringsWithNoLoggedUser);
            return true;
        }
        return false;
    }
}
