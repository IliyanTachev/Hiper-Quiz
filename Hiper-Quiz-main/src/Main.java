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

public class Main {
    public static void main(String[] args) throws EntityAlreadyExistsException {

        // Create Users
        User[] users = {
                new User("iliqn@gmail.com", "iliqn123", "12345", Gender.MALE),
                new User("maria40@gmail.com", "maria45", "dfjkgd", Gender.FEMALE),
                new User("mitko@gmail.com", "mitko_nik", "nik_mitko-1234", Gender.MALE),
                new User("peshkoeqk", "petshko@gamil.com", "ot1do9", Gender.MALE)
        };

        // Create Quizzes
        Quiz[] quizzes = {
                new Quiz("Presidents of the USA", users[0], "Questions on presidents of the USA", 2, "presidents, usa"),
                new Quiz("Presidents of Bulgaria", users[1], "Questions on presidents of Bulgaria", 4, "presidents, bulgaria"),
                new Quiz("Presidents of Russia", users[2], "Questions on presidents of Russia", 3, "presidents, russia")
        };

        // USA
        Question[] questionsAboutUSAPresidents = {
                new Question(quizzes[0], "Who is the first president of USA?"),
                new Question(quizzes[0], "Who is the current president of USA?")
        };

        // Set Answers
        questionsAboutUSAPresidents[0].setAnswers(List.of(
                new Answer(questionsAboutUSAPresidents[0], "Donald Trump", 0),
                new Answer(questionsAboutUSAPresidents[0], "George Washington",  1),
                new Answer(questionsAboutUSAPresidents[0], "Joe Biden",  0)
        ));

        questionsAboutUSAPresidents[1].setAnswers(List.of(
                new Answer(questionsAboutUSAPresidents[1], "Elon Musk", 0),
                new Answer(questionsAboutUSAPresidents[1], "Joe Rogan",  0),
                new Answer(questionsAboutUSAPresidents[1], "Joe Biden",  1)
        ));

        // Bulgaria
        Question[] questionsAboutBulgarianPresidents = {
                new Question(quizzes[1], "Who is the first president of Bulgaria?"),
                new Question(quizzes[1], "Who is the current president of Bulgaria?"),
                new Question(quizzes[1], "Who was the 4th president of Bulgaria?"),
                new Question(quizzes[1], "Who was the second president of Bulgaria?")
        };

        // Set Answers
        questionsAboutBulgarianPresidents[0].setAnswers(List.of(
                new Answer(questionsAboutBulgarianPresidents[0], "Zhelyu Zhelev", 1),
                new Answer(questionsAboutBulgarianPresidents[0], "Boyko Borissov",  0),
                new Answer(questionsAboutBulgarianPresidents[0], "Joe Biden",  0)
        ));

        questionsAboutBulgarianPresidents[1].setAnswers(List.of(
                new Answer(questionsAboutBulgarianPresidents[1], "George Purvanov", 0),
                new Answer(questionsAboutBulgarianPresidents[1], "Rosen Plevneliev",  0),
                new Answer(questionsAboutBulgarianPresidents[1], "Rumen Radev",  1)
        ));

        questionsAboutBulgarianPresidents[2].setAnswers(List.of(
                new Answer(questionsAboutBulgarianPresidents[2], "Rosen Plevneliev", 1),
                new Answer(questionsAboutBulgarianPresidents[2], "Kubrat Pulev",  0),
                new Answer(questionsAboutBulgarianPresidents[2], "Zhelyu Zhelev",  0)
        ));

        questionsAboutBulgarianPresidents[3].setAnswers(List.of(
                new Answer(questionsAboutBulgarianPresidents[3], "Rumen Radev", 0),
                new Answer(questionsAboutBulgarianPresidents[3], "Geroge Purvanov",  0),
                new Answer(questionsAboutBulgarianPresidents[3], "Petar Stoyanov",  1)
        ));

        // Russia

        Question[] questionsAboutRussianPresidents = {
                new Question(quizzes[2], "Who is the current president of Russia?"),
                new Question(quizzes[2], "Who was the 3th president of Russia?"),
                new Question(quizzes[2], "Who was the second president of Russia?")
        };

        // Set Answers
        questionsAboutRussianPresidents[0].setAnswers(List.of(
                new Answer(questionsAboutRussianPresidents[0], "George Purvanov", 0),
                new Answer(questionsAboutRussianPresidents[0], "Vladimir Moronov",  0),
                new Answer(questionsAboutRussianPresidents[0], "Vladimir Putin",  1)
        ));

        questionsAboutRussianPresidents[1].setAnswers(List.of(
                new Answer(questionsAboutRussianPresidents[1], "Boris Yeltsin", 0),
                new Answer(questionsAboutRussianPresidents[1], "Vladimir Putin",  0),
                new Answer(questionsAboutRussianPresidents[1], "Dmitry Medvedev",  1)
        ));

        questionsAboutRussianPresidents[2].setAnswers(List.of(
                new Answer(questionsAboutRussianPresidents[2], "Vladimir Putin", 1),
                new Answer(questionsAboutRussianPresidents[2], "mitry Medvedev",  0),
                new Answer(questionsAboutRussianPresidents[2], "Boris Yeltsin",  0)
        ));

        quizzes[0].setQuestions(Arrays.asList(questionsAboutUSAPresidents)); // Quiz about USA presidents
        quizzes[1].setQuestions(Arrays.asList(questionsAboutBulgarianPresidents)); // Quiz about Bulgarian presidents
        quizzes[2].setQuestions(Arrays.asList(questionsAboutRussianPresidents)); // Quiz about Russian presidents

        users[0].addQuiz(quizzes[0]);
        users[1].addQuiz(quizzes[1]);
        users[2].addQuiz(quizzes[2]);

        UserService userService = new UserServiceImpl(new UserRepositoryInMemoryImpl(new LongKeyGenerator()));
        Arrays.asList(users).stream().forEach(u -> {
            try {
                userService.createUser(u);
            } catch (EntityAlreadyExistsException e) {
                e.printStackTrace();
            }
        });

        // Saving quizzes
        QuizRepository quizRepository = new QuizRepositoryInMemoryImpl(new LongKeyGenerator());
        Arrays.asList(quizzes).stream().forEach(quiz -> {
            try {
                quizRepository.create(quiz);
            } catch (EntityAlreadyExistsException e) {
                e.printStackTrace();
            }
        });

        // Saving questions
        QuestionRepository questionRepository = new QuestionRepositoryInMemoryImpl(new LongKeyGenerator());
        for(Quiz quiz : quizzes){
            for(Question question : quiz.getQuestions()){
                try {
                    questionRepository.create(question);
                } catch (EntityAlreadyExistsException e) {
                    e.printStackTrace();
                }
            }
        }

        // Saving answers
        AnswerRepository answerRepository = new AnswerRepositoryInMemoryImpl(new LongKeyGenerator());
        for(Quiz quiz : quizzes){
            for(Question question : quiz.getQuestions()){
                for(Answer answer : question.getAnswers()) {
                    try {
                        answerRepository.create(answer);
                    } catch (EntityAlreadyExistsException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Common entity metadata column descriptors
        List<PrintUtil.ColumnDescriptor> metadataColumns = List.of(
                new PrintUtil.ColumnDescriptor("created", "Created", 19, CENTER),
                new PrintUtil.ColumnDescriptor("modified", "Modified", 19, CENTER)
        );

        // Print formatted report as table
        List<PrintUtil.ColumnDescriptor> userColumns = new ArrayList<>(List.of(
                new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
                new PrintUtil.ColumnDescriptor("username", "Username", 12, LEFT),
                new PrintUtil.ColumnDescriptor("email", "Email", 15, LEFT),
                new PrintUtil.ColumnDescriptor("password", "Password", 12, LEFT),
                new PrintUtil.ColumnDescriptor("gender", "Gender", 6, RIGHT, 2),
                new PrintUtil.ColumnDescriptor("role", "Role", 13, CENTER),
                new PrintUtil.ColumnDescriptor("picture", "Picture", 12, CENTER),
                new PrintUtil.ColumnDescriptor("description", "Description", 12, CENTER),
                new PrintUtil.ColumnDescriptor("metadata", "Metadata ", 12, CENTER),
                new PrintUtil.ColumnDescriptor("status", "Status", 6, CENTER),
                new PrintUtil.ColumnDescriptor("quizzes", "Quizzes", 20, CENTER)
        ));

        // Print formatted report as table
        List<PrintUtil.ColumnDescriptor> quizColumns = new ArrayList<>(List.of(
                new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
                new PrintUtil.ColumnDescriptor("title", "Title", 22, LEFT),
                new PrintUtil.ColumnDescriptor("author", "Author", 7, CENTER),
                new PrintUtil.ColumnDescriptor("description", "Description", 35, LEFT),
                new PrintUtil.ColumnDescriptor("questions", "Questions", 9, LEFT, 2),
                new PrintUtil.ColumnDescriptor("expectedDuration", "ExpectedDuration", 16, CENTER),
                new PrintUtil.ColumnDescriptor("picture", "Picture", 12, CENTER),
                new PrintUtil.ColumnDescriptor("tags", "Tags", 20, LEFT)

        ));

        // Print formatted report as table
        List<PrintUtil.ColumnDescriptor> questionColumns = new ArrayList<>(List.of(
                new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
                new PrintUtil.ColumnDescriptor("quiz", "Quiz", 5, CENTER),
                new PrintUtil.ColumnDescriptor("text", "Text", 40, LEFT),
                new PrintUtil.ColumnDescriptor("picture", "Picture", 12, LEFT),
                new PrintUtil.ColumnDescriptor("answers", "Answers", 10, LEFT, 2)
        ));

        // Print formatted report as table
        List<PrintUtil.ColumnDescriptor> answerColumns = new ArrayList<>(List.of(
                new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
                new PrintUtil.ColumnDescriptor("question", "Question", 12, LEFT),
                new PrintUtil.ColumnDescriptor("text", "Text", 15, LEFT),
                new PrintUtil.ColumnDescriptor("picture", "Picture", 12, LEFT),
                new PrintUtil.ColumnDescriptor("score", "Score", 6, RIGHT, 2)
        ));

        userColumns.addAll(metadataColumns);
        quizColumns.addAll(metadataColumns);
        questionColumns.addAll(metadataColumns);
        answerColumns.addAll(metadataColumns);

        String userReport = PrintUtil.formatTable(userColumns, userService.getAllUsers(), "Users List:");
        String quizReport = PrintUtil.formatTable(quizColumns, quizRepository.findAll(), "Quizes List:");
        String questionReport = PrintUtil.formatTable(questionColumns, questionRepository.findAll(), "Questions List:");
        String answerReport = PrintUtil.formatTable(answerColumns, answerRepository.findAll(), "Answers List:");

        System.out.println(userReport);
        System.out.println(quizReport);
        System.out.println(questionReport);
        System.out.println(answerReport);


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
