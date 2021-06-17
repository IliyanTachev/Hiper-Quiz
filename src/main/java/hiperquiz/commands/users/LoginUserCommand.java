package hiperquiz.commands.users;

import hiperquiz.exception.EntityNotFoundException;
import hiperquiz.entities.LoginUser;
import hiperquiz.entities.User;
import hiperquiz.services.UserService;
import hiperquiz.view.Command;
import hiperquiz.view.InputUtils;
import hiperquiz.view.MainMenu;
import hiperquiz.view.MenuItemStrings;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LoginUserCommand implements Command{
    private UserService userService;
    private Scanner in;

    public LoginUserCommand(UserService userService, Scanner in) {
        this.userService = userService;
        this.in = in;
    }

    @Override
    public boolean execute() {
        LoginUser loginUserDetails = InputUtils.inputLoginUser(in);
        try {
            User loggedUser = userService.login(loginUserDetails);
            System.out.println("Successfully logged as " + loggedUser.getUsername());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainMenu.loadMenuItems(MenuItemStrings.menuItemStringsWithLoggedUser);
            return true;
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }
}
