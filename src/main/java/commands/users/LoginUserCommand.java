package commands.users;

import exception.EntityNotFoundException;
import model.LoginUser;
import model.User;
import services.UserService;
import view.Command;
import view.InputUtils;
import view.MainMenu;
import view.MenuItemStrings;

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
