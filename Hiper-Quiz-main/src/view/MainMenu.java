package view;

import controller.CommandRegister;
import dao.impl.LongKeyGenerator;
import dao.impl.UserRepositoryInMemoryImpl;
import exception.EntityAlreadyExistsException;
import model.User;
import services.AnswerService;
import services.QuestionService;
import services.QuizService;
import services.UserService;
import services.impl.UserServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static view.MenuCommand.*;

public class MainMenu {
    private final Scanner in;
    private static final String[] menuItemStrings = {
            "EXIT from this program = EXIT",
            "Add user = ADD_USER",
            "Edit user = EDIT_USER",
            "View user = VIEW_USER",
            "List all users = LIST_ALL_USERS"};

    private List<MenuItem> menuItems = new ArrayList<>();
    private Map<MenuCommand, Command> commands = new EnumMap<MenuCommand, Command>(MenuCommand.class);
    private CommandRegister commandRegister;

    public MainMenu(CommandRegister commandRegister, InputStream inputStream) {
        this.commandRegister = commandRegister;
        this.in = new Scanner(inputStream);
        // Load menuItems
        Arrays.stream(menuItemStrings).forEach(menuItemStringLine -> menuItems.add(parseMenuItemString(menuItemStringLine)));

       // EXIT, LOGIN, REGISTER, ADD_USER, VIEW_USER, EDIT_USER, DELETE_USER, LIST_ALL_USERS
        // Load commands
        commands.put(EXIT, new Command() {
            @Override
            public boolean execute() {
                System.exit(0);
                return true;
            }
        });

        commands.put(LOGIN, new Command() {
            @Override
            public boolean execute() {
                System.exit(0);
                return true;
            }
        });

        commands.put(REGISTER, new Command() {
            @Override
            public boolean execute() {
                System.exit(0);
                return true;
            }
        });

        commands.put(ADD_USER, new Command() {
            @Override
            public boolean execute() {
                System.out.println("< Enter new User details >");
                User user = InputUtils.inputUser(in);
                try {
                    commandRegister.createUser(user);
                    System.out.println("User was created successfully.");
                    return true;
                } catch (EntityAlreadyExistsException e) {
                    System.err.println(e.getMessage());
                    return false;
                }
            }
        });

        commands.put(VIEW_USER, new Command() {
            @Override
            public boolean execute() {
                System.out.println("Enter User ID: ");
                Optional<User> user = commandRegister.getUserById(in.nextLong());
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
        });

        commands.put(EDIT_USER, new Command() {
            @Override
            public boolean execute() {
                System.exit(0);
                return true;
            }
        });

        commands.put(DELETE_USER, new Command() {
            @Override
            public boolean execute() {
                System.exit(0);
                return true;
            }
        });

        commands.put(LIST_ALL_USERS, new Command() {
            @Override
            public boolean execute() {
                List<User> allUsers =  commandRegister.getAllUsers();
                System.out.println(OutputUtils.printAllUsers(allUsers));
                return true;
            }
        });
    }

    public void start(){
        while(true){
            System.out.println("           M A I N    M E N U");
            System.out.println("=========================================");
            int indx = 1;
            for(MenuItem menuItem : menuItems) {
                System.out.println(indx + ". " + menuItem.getLabel());
                indx++;
            }

            System.out.println("Enter commandId: ");
            int commandId = in.nextInt();
            commands.get(menuItems.get(commandId - 1).getMenuCommand()).execute();

            // Clear console
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private MenuItem parseMenuItemString(String menuItemString){
        String[] parsedMenuItem = menuItemString.split("=");
        return new MenuItem(parsedMenuItem[0].trim(), MenuCommand.valueOf(parsedMenuItem[1].trim()));
    }
}
