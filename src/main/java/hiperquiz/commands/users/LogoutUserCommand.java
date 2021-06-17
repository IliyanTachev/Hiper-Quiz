package hiperquiz.commands.users;

import hiperquiz.services.UserService;
import hiperquiz.view.Command;
import hiperquiz.view.MainMenu;
import hiperquiz.view.MenuItemStrings;

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
