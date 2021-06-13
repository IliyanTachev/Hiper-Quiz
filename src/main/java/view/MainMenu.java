package view;

import commands.common.ExitMenuCommand;
import commands.common.LoadEntitiesCommand;
import commands.quizzes.*;
import commands.users.*;
import dao.*;
import services.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

import static view.MenuCommand.*;

public class MainMenu {
    private final Scanner in;

    private static List<MenuItem> menuItems = new ArrayList<>();
    private Map<MenuCommand, Command> commands = new EnumMap<MenuCommand, Command>(MenuCommand.class);

    UserService userService;
    QuizService quizService;
    QuestionService questionService;
    AnswerService answerService;
    QuizResultService quizResultService;

    public MainMenu(InputStream inputStream, UserService userService, QuizService quizService, QuestionService questionService, AnswerService answerService, QuizResultService quizResultService) {
        this.in = new Scanner(inputStream);

        this.userService = userService;
        this.quizService = quizService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.quizResultService = quizResultService;

        // Load menuItems
        loadMenuItems(MenuItemStrings.menuItemStringsWithNoLoggedUser);

        // Load commands
//        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
//        classLoadersList.add(ClasspathHelper.contextClassLoader());
//        classLoadersList.add(ClasspathHelper.staticClassLoader());
//
//        Reflections reflections = new Reflections(new ConfigurationBuilder()
//                .setUrls(ClasspathHelper.forPackage("commands"))
//                .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner())
//                .filterInputsBy(new FilterBuilder().includePackage("commands")));
//
//        Set<Class<? extends Command>> allClasses =
//                reflections.getSubTypesOf(Command.class);
//
//        allClasses.stream().forEach(c -> {
//                    List<Field> fields = Arrays.asList(c.getDeclaredFields()).stream().filter(f -> f.getName().indexOf("Service") != -1).collect(Collectors.toList());
//                    List<Class<?>> classes = fields.stream().map(f -> {
//                        try {
//                            return Class.forName(f.getType().getName());
//                        } catch (ClassNotFoundException e) {
//                            e.printStackTrace();
//                        }
//                        return null;
//                    }).collect(Collectors.toList());
//
//            try {
//                c.getDeclaredConstructor(classes.toArray(new Class[classes.size()]));
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//            return;
//        });
//
//        Class<?> neshto = null;
//        try {
//            neshto = Class.forName("services.QuizService");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(neshto.getName());

//        allClasses.stream().forEach(c -> {
//            Constructor constructor = null;
//            try {
//                constructor = c.getDeclaredConstructor(Class.forName("QuizService.class"), Class.forName("QuestionService.class"), Class.forName("AnswerService.class"), Class.forName("Scanner.class"));
//            } catch (NoSuchMethodException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            System.out.println(constructor);
//
////          System.out.println(constructor);
//        });

        commands.put(EXIT, new ExitMenuCommand(userService, quizService, questionService, answerService, quizResultService));
        commands.put(ADD_QUIZ, new CreateQuizCommand(quizService, userService, questionService, answerService, in));
        commands.put(EDIT_QUIZ, new EditQuizCommand(quizService));
        commands.put(LIST_ALL_QUIZ_RESULTS, new ListAllQuizResultsCommand(quizResultService));
        commands.put(LIST_ALL_QUIZZES, new ListAllQuizzesCommand(quizService));
        commands.put(PLAY_QUIZ, new PlayQuizCommand(quizService, userService, answerService, quizResultService, in));
        commands.put(VIEW_QUIZ, new ViewQuizCommand(quizService));
        commands.put(ADD_USER, new CreateUserCommand(userService, in));
        commands.put(DELETE_USER, new DeleteUserCommand());
        commands.put(EDIT_USER, new EditUserCommand(userService));
        commands.put(LIST_ALL_USERS, new ListAllUsersCommand(userService));
        commands.put(LOGIN_USER, new LoginUserCommand(userService, in));
        commands.put(LOGOUT_USER, new LogoutUserCommand(userService));
        commands.put(REGISTER_USER, new RegisterUserCommand(userService, in));
        commands.put(VIEW_USER, new ViewUserCommand(userService, in));


    }

    public void start(){
        // Load DB from file
        try {
            LoadEntitiesCommand loadEntitiesCommand =
                    new LoadEntitiesCommand(
                            new FileInputStream("hiperQuiz.db"),
                            (UserRepository) userService.getRepository(),
                            (QuizRepository) quizService.getRepository(),
                            (QuestionRepository) questionService.getRepository(),
                            (AnswerRepository) answerService.getRepository(),
                            (QuizResultRepository) quizResultService.getRepository()
                    );
            loadEntitiesCommand.execute();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(true){
            if(userService.getLoggedUser() != null){ // if there is loggedUser
                System.out.println("=========================================");
                System.out.println("Hello, " + userService.getLoggedUser().getUsername());
            }
            System.out.println("=========================================");
            System.out.println("           M A I N    M E N U");
            System.out.println("=========================================");
            int indx = 1;
            for(MenuItem menuItem : menuItems) {
                System.out.println(indx + ". " + menuItem.getLabel());
                indx++;
            }

            System.out.println("Enter commandId: ");
            int commandId = Integer.parseInt(in.nextLine().trim());
            commands.get(menuItems.get(commandId - 1).getMenuCommand()).execute();

            // Clear console
        }
    }

    private static MenuItem parseMenuItemString(String menuItemString){
        String[] parsedMenuItem = menuItemString.split("=");
        return new MenuItem(parsedMenuItem[0].trim(), MenuCommand.valueOf(parsedMenuItem[1].trim()));
    }

    public static void loadMenuItems(String[] menuItemStrings){
        menuItems.clear();
        Arrays.stream(menuItemStrings).forEach(menuItemStringLine -> menuItems.add(parseMenuItemString(menuItemStringLine)));
    }
}
