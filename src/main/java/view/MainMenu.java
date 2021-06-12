package view;

import commands.LoadEntitiesCommand;
import commands.SaveEntitiesCommand;
import controller.CommandRegister;
import dao.AnswerRepository;
import dao.QuestionRepository;
import dao.QuizRepository;
import dao.UserRepository;
import exception.EntityAlreadyExistsException;
import exception.EntityNotFoundException;
import model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static view.MenuCommand.*;

public class MainMenu {
    private final Scanner in;
    private static final String[] menuItemStringsWithNoLoggedUser = {
            "EXIT from this program = EXIT",
            "Log in = LOGIN_USER",
            "Register = REGISTER_USER",
    };

    private static final String[] menuItemStringsWithLoggedUser = {
            "EXIT from this program = EXIT",
            "LOGOUT = LOGOUT_USER",
            "Add user = ADD_USER",
            "Edit user = EDIT_USER",
            "View user = VIEW_USER",
            "List all users = LIST_ALL_USERS",
            "Add quiz = ADD_QUIZ",
            "Edit quiz = EDIT_QUIZ",
            "View quiz = VIEW_QUIZ",
            "List all quizzes = LIST_ALL_QUIZZES",
            "List all quiz results = LIST_ALL_QUIZ_RESULTS",
            "Play Quiz = PLAY_QUIZ"
    };

    private List<MenuItem> menuItems = new ArrayList<>();
    private Map<MenuCommand, Command> commands = new EnumMap<MenuCommand, Command>(MenuCommand.class);
    private CommandRegister commandRegister;

    UserRepository userRepository;
    QuizRepository quizRepository;
    QuestionRepository questionRepository;
    AnswerRepository answerRepository;

    public MainMenu(CommandRegister commandRegister, InputStream inputStream) {
        this.commandRegister = commandRegister;
         userRepository = this.commandRegister.getUserRepo();
         quizRepository = this.commandRegister.getQuizRepo();
         questionRepository = this.commandRegister.getQuestionRepo();
         answerRepository = this.commandRegister.getAnswerRepo();
        this.in = new Scanner(inputStream);
        // Load menuItems
        loadMenuItems(menuItemStringsWithNoLoggedUser);

       // EXIT, LOGIN, REGISTER, ADD_USER, VIEW_USER, EDIT_USER, DELETE_USER, LIST_ALL_USERS
        // Load commands
        commands.put(EXIT, new Command() {
            @Override
            public boolean execute() {
                SaveEntitiesCommand saveCommand = null;
                try {
                    saveCommand = new SaveEntitiesCommand(new FileOutputStream("hiperQuiz.db"),
                            userRepository, quizRepository, questionRepository, answerRepository);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                saveCommand.execute();
                System.exit(0);
                return true;
            }
        });

        commands.put(LOGIN_USER, new Command() {
            @Override
            public boolean execute() {
                LoginUser loginUserDetails = InputUtils.inputLoginUser(in);
                try {
                    User loggedUser = commandRegister.loginUser(loginUserDetails);
                    System.out.println("Successfully logged as " + loggedUser.getUsername());
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    loadMenuItems(menuItemStringsWithLoggedUser);
                    return true;
                } catch (EntityNotFoundException e) {
                    System.err.println(e.getMessage());
                }
                return true;
            }
        });

        commands.put(LOGOUT_USER, new Command() {
            @Override
            public boolean execute() {
                if(commandRegister.logoutUser() == null){
                    loadMenuItems(menuItemStringsWithNoLoggedUser);
                    return true;
                }
                return false;
            }
        });

        commands.put(REGISTER_USER, new Command() {
            @Override
            public boolean execute() {
                try {
                    User registeredUser = commandRegister.registerUser(InputUtils.inputUser(in));
                    System.out.println("User was registered successfully.");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Successfully logged as " + registeredUser.getUsername());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    loadMenuItems(menuItemStringsWithLoggedUser);
                    return true;
                } catch (EntityAlreadyExistsException e) {
                    // e.printStackTrace();
                    System.err.println("[ERROR] User is already registered.");
                } catch (EntityNotFoundException e) {
                    System.err.println("[ERROR] Registration process failed.");
                }
                return false;
            }
        });

        commands.put(ADD_USER, new Command() {
            @Override
            public boolean execute() {
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

        commands.put(ADD_QUIZ, new Command() {
            @Override
            public boolean execute() {
                Quiz quiz = InputUtils.inputQuiz(in);
                quiz.setAuthor(commandRegister.getLoggedUser());
                try {
                    Quiz createdQuiz = commandRegister.createQuiz(quiz);
                    System.out.println("Quiz(\"" + createdQuiz.getTitle() + "\") was successfully created.");
                } catch (EntityAlreadyExistsException e) {
//                    e.printStackTrace();
                    System.err.println(e.getMessage());
                }
                return true;
            }
        });

        commands.put(EDIT_QUIZ, new Command() {
            @Override
            public boolean execute() {
                return false;
            }
        });

        commands.put(VIEW_QUIZ, new Command() {
            @Override
            public boolean execute() {
                return false;
            }
        });

        commands.put(LIST_ALL_QUIZZES, new Command() {
            @Override
            public boolean execute() {
                List<Quiz> allQuizzes =  commandRegister.getAllQuizzes();
                System.out.println(OutputUtils.printAllQuizzes(allQuizzes));
                return true;
            }
        });

        commands.put(LIST_ALL_QUIZ_RESULTS, new Command() {
            @Override
            public boolean execute() {
                List<QuizResult> quizResults = commandRegister.getAllQuizResults();
                System.out.println(OutputUtils.formatAllQuizResults(quizResults));
                return true;
            }
        });

        commands.put(PLAY_QUIZ, new Command() {
            @Override
            public boolean execute() {
                System.out.println(OutputUtils.printAllQuizzes(commandRegister.getAllQuizzes())); // Print all Quizzes
                System.out.println("Enter Quiz ID: ");
                Long selectedQuizId = Long.parseLong(in.nextLine());
                Optional<Quiz> fetchedQuiz = commandRegister.getQuizById(selectedQuizId);
                if(fetchedQuiz.isEmpty()) {
                    System.err.println("Please enter valid Quiz ID.");
                    return false;
                }
                int correctAnswersCounter = 0;
                int correctAnswersTotalScore = 0;
                List<Long> correctAnswersAssumptionIds = InputUtils.inputQuizGame(in, fetchedQuiz.get());
                for(Long answerId : correctAnswersAssumptionIds){
                    int answerScore = commandRegister.getAnswerById(answerId).get().getScore();
                    if(answerScore > 0) {
                        correctAnswersCounter++;
                        correctAnswersTotalScore += answerScore;
                    }
                }

                User loggedUser = commandRegister.getLoggedUser();
                QuizResult result = new QuizResult(loggedUser, fetchedQuiz.get(), correctAnswersTotalScore);
                if(loggedUser instanceof Player) ((Player) loggedUser).addResult(result);

                try {
                    commandRegister.createQuizResult(result);
                } catch (EntityAlreadyExistsException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("You scored %d / %d correct answers.", correctAnswersCounter, fetchedQuiz.get().getQuestionsSize()));
                System.out.println(OutputUtils.formatQuizResult(result));
                return true;
            }
        });

    }

    public void start(){
        try {
            LoadEntitiesCommand loadEntitiesCommand = new LoadEntitiesCommand(new FileInputStream("hiperQuiz.db"), userRepository, quizRepository, questionRepository, answerRepository);
            loadEntitiesCommand.execute();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(true){
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

    private MenuItem parseMenuItemString(String menuItemString){
        String[] parsedMenuItem = menuItemString.split("=");
        return new MenuItem(parsedMenuItem[0].trim(), MenuCommand.valueOf(parsedMenuItem[1].trim()));
    }

    private void loadMenuItems(String[] menuItemStrings){
        menuItems.clear();
        Arrays.stream(menuItemStrings).forEach(menuItemStringLine -> menuItems.add(parseMenuItemString(menuItemStringLine)));
    }
}
