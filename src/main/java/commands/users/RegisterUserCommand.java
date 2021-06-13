package commands.users;

import exception.EntityAlreadyExistsException;
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

public class RegisterUserCommand implements Command {
    private UserService userService;
    private Scanner in;

    public RegisterUserCommand(UserService userService, Scanner in) {
        this.userService = userService;
        this.in = in;
    }

    @Override
    public boolean execute() {
        try {
            User registeredUser = userService.create(InputUtils.inputUser(in));
            User loggedUser = userService.login(new LoginUser(registeredUser.getUsername(), registeredUser.getPassword()));
            System.out.println("User was registered successfully.");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Successfully logged as " + loggedUser.getUsername());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainMenu.loadMenuItems(MenuItemStrings.menuItemStringsWithLoggedUser);
            return true;
        } catch (EntityAlreadyExistsException e) {
            // e.printStackTrace();
            System.err.println("[ERROR] User is already registered.");
        } catch (EntityNotFoundException e) {
            System.err.println("[ERROR] Registration process failed.");
        }
        return false;
    }
}
