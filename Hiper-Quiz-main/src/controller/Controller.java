package controller;

import dao.AnswerRepository;
import dao.QuestionRepository;
import dao.QuizRepository;
import dao.UserRepository;
import dao.impl.*;
import exception.EntityAlreadyExistsException;
import exception.EntityNotFoundException;
import model.*;
import services.UserService;
import services.impl.UserServiceImpl;
import util.ConsoleReader;
import util.InputReader;
import util.PrintUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static util.Alignment.*;

public class Controller {

    public static void main(String[] args) throws EntityAlreadyExistsException {

        // PRINTING
        // Common entity metadata column descriptors
//        List<PrintUtil.ColumnDescriptor> metadataColumns = List.of(
//                new PrintUtil.ColumnDescriptor("created", "Created", 19, CENTER),
//                new PrintUtil.ColumnDescriptor("modified", "Modified", 19, CENTER)
//        );

        // Print formatted report as table
//        List<PrintUtil.ColumnDescriptor> userColumns = new ArrayList<>(List.of(
//                new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
//                new PrintUtil.ColumnDescriptor("username", "Username", 12, LEFT),
//                new PrintUtil.ColumnDescriptor("email", "Email", 15, LEFT),
//                new PrintUtil.ColumnDescriptor("password", "Password", 12, LEFT),
//                new PrintUtil.ColumnDescriptor("gender", "Gender", 6, RIGHT, 2),
//                new PrintUtil.ColumnDescriptor("role", "Role", 13, CENTER),
//                new PrintUtil.ColumnDescriptor("picture", "Picture", 12, CENTER),
//                new PrintUtil.ColumnDescriptor("description", "Description", 12, CENTER),
//                new PrintUtil.ColumnDescriptor("metadata", "Metadata ", 12, CENTER),
//                new PrintUtil.ColumnDescriptor("status", "Status", 6, CENTER),
//                new PrintUtil.ColumnDescriptor("quizzes", "Quizzes", 20, CENTER)
//        ));

        // Print formatted report as table
//        List<PrintUtil.ColumnDescriptor> quizColumns = new ArrayList<>(List.of(
//                new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
//                new PrintUtil.ColumnDescriptor("title", "Title", 22, LEFT),
//                new PrintUtil.ColumnDescriptor("author", "Author", 7, CENTER),
//                new PrintUtil.ColumnDescriptor("description", "Description", 35, LEFT),
//                new PrintUtil.ColumnDescriptor("questions", "Questions", 9, LEFT, 2),
//                new PrintUtil.ColumnDescriptor("expectedDuration", "ExpectedDuration", 16, CENTER),
//                new PrintUtil.ColumnDescriptor("picture", "Picture", 12, CENTER),
//                new PrintUtil.ColumnDescriptor("tags", "Tags", 20, LEFT)
//
//        ));

        // Print formatted report as table
//        List<PrintUtil.ColumnDescriptor> questionColumns = new ArrayList<>(List.of(
//                new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
//                new PrintUtil.ColumnDescriptor("quiz", "Quiz", 5, CENTER),
//                new PrintUtil.ColumnDescriptor("text", "Text", 40, LEFT),
//                new PrintUtil.ColumnDescriptor("picture", "Picture", 12, LEFT),
//                new PrintUtil.ColumnDescriptor("answers", "Answers", 10, LEFT, 2)
//        ));

        // Print formatted report as table
//        List<PrintUtil.ColumnDescriptor> answerColumns = new ArrayList<>(List.of(
//                new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
//                new PrintUtil.ColumnDescriptor("question", "Question", 12, LEFT),
//                new PrintUtil.ColumnDescriptor("text", "Text", 15, LEFT),
//                new PrintUtil.ColumnDescriptor("picture", "Picture", 12, LEFT),
//                new PrintUtil.ColumnDescriptor("score", "Score", 6, RIGHT, 2)
//        ));

//        userColumns.addAll(metadataColumns);
//        quizColumns.addAll(metadataColumns);
//        questionColumns.addAll(metadataColumns);
//        answerColumns.addAll(metadataColumns);

//        String userReport = PrintUtil.formatTable(userColumns, userService.getAllUsers(), "Users List:");
//        String quizReport = PrintUtil.formatTable(quizColumns, quizRepository.findAll(), "Quizes List:");
//        String questionReport = PrintUtil.formatTable(questionColumns, questionRepository.findAll(), "Questions List:");
//        String answerReport = PrintUtil.formatTable(answerColumns, answerRepository.findAll(), "Answers List:");
//
//        System.out.println(userReport);
//        System.out.println(quizReport);
//        System.out.println(questionReport);
//        System.out.println(answerReport);


        // Menu
        InputReader consoleReader = new ConsoleReader();
        while(true){
            Long userId;
            printMenuOptions();
            // Switch by commandId
            switch(consoleReader.readInput()){
                case 1:
                    User user = consoleReader.readUserDetails();
                    userService.createUser(user);
                    break;
                case 2:
                    System.out.println("Enter ID: ");
                    userId = ((ConsoleReader)consoleReader).getScanner().nextLong();
                    Optional<User> userFound = userService.getUserById(userId);
                    if(userFound.isPresent()) System.out.println(userFound.toString());
                    break;
                case 3:
                    while(true){
                        System.out.println("Enter ID: ");
                        userId = ((ConsoleReader)consoleReader).getScanner().nextLong();
                        User fetchedUser = consoleReader.readUserDetails();
                        fetchedUser.setId(userId);
                        try {
                            userService.updateUser(fetchedUser);
                            break;
                        } catch (EntityNotFoundException ignored) {
                        }
                    }
                    break;
                case 4:
                    while(true){
                        System.out.println("Enter ID: ");
                        userId = ((ConsoleReader)consoleReader).getScanner().nextLong();
                        try {
                            userService.deleteUser(userId);
                            break;
                        } catch (EntityNotFoundException ignored) {
                        }
                    }
                    break;
                case 5:
                    userService.getAllUsers().forEach(u -> System.out.println(u.toString()));
                    break;
            };
        }
    }

    private static void printMenuOptions(){
        System.out.println("Choose command:");
        System.out.println("-----------------------");
        System.out.println("1. Create User");
        System.out.println("2. Read User");
        System.out.println("3. Update User");
        System.out.println("4. Delete User");
        System.out.println("5. List Users");
    }
}
