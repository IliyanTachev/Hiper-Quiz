package view;

import model.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputUtils {
    public static User inputUser(Scanner in){
        String username;
        String email;
        String password;
        String gender;
        String description;
        String metadata;
        String picture;

        System.out.println("< Create new User >");

        do {
            System.out.println("Enter username (length between 2 & 15): ");
            username = in.nextLine();
        } while(username.length() < 2 || username.length() > 15);

        do {
            System.out.print("Enter email (valid email): ");
            email = in.nextLine();
        } while(!isValidEmail(email));

        do {
            System.out.print("Enter password: ");
            password = in.nextLine();
        } while(!isValidPassword(password));

        String genderStr;
        do {
            System.out.print("Enter gender (MALE/FEMALE): ");
            gender = in.nextLine();
        } while(!gender.equals("MALE") && !gender.equals("FEMALE"));

        do {
            System.out.print("Enter description: ");
            description = in.nextLine();
        } while(description.length() < 20 || description.length() > 250);

        System.out.print("Picture: ");
        picture = in.nextLine();

        do{
            System.out.print("Enter metadata: ");
            metadata = in.nextLine();
        } while(metadata.length() < 0 || metadata.length() > 512);

        User createdUser = new User(username, email, password, Gender.valueOf(gender), picture, description, metadata);
        return createdUser;
    }

    public static LoginUser inputLoginUser(Scanner in){
        String username;
        String password;

        do {
            System.out.println("Enter username: ");
            username = in.nextLine();
        } while(username.length() < 2 || username.length() > 15);

        do {
            System.out.println("Enter password: ");
            password = in.nextLine();
        } while(!isValidPassword(password));

        return new LoginUser(username, password);
    }

    public static Quiz inputQuiz(Scanner in){
        String title;
        String description;
        int expectedDuration;
        String picture;
        String tags;

        System.out.println("<- Create new Quiz ->");
        do {
            System.out.println("Enter title: ");
            title = in.nextLine();
        } while(title.length() < 2 || title.length() > 80);

        do {
            System.out.println("Enter description: ");
            description = in.nextLine();
        } while(description.length() < 20 || description.length() > 250);

        System.out.println("Enter expectedDuration: ");
        expectedDuration = Integer.parseInt(in.nextLine());

        System.out.println("Picture: ");
        picture = in.nextLine();

        System.out.println("Tags (comma separated tags): ");
        tags = in.nextLine();

        Quiz quiz = new Quiz(title, description, expectedDuration, picture, tags);
        System.out.println("==================================================");
        System.out.println("You must enter at least 1 question for this quiz.");

        List<Question> questions = new ArrayList<>();
        int i=0;
        while(true){
            if(i >= 1) {
                System.out.println("Do you want to add one more Question (\"yes\" or \"no\")?");
                String command = in.nextLine();
                if(command.equals("no")) break;
            }
            questions.add(inputQuestion(in, quiz, i+1));
            i++;
        }

        quiz.setQuestions(questions);
        return quiz;
    }

    public static Question inputQuestion(Scanner in, Quiz assignQuiz, int currentNumber){
        Quiz quiz = assignQuiz;
        String text;
        String picture;

        System.out.println("<- Create new Question (№" + currentNumber + ") ->");
        do {
            System.out.println("Enter text: ");
            text = in.nextLine();
        } while(text.length() < 10 || text.length() > 300);

        System.out.println("Picture: ");
        picture = in.nextLine();

        Question question = new Question(quiz, text, picture);
        System.out.println("======================================================");
        System.out.println("You must enter at least 2 answers for this question.");

        List<Answer> answers = new ArrayList<>();
        int i=0;
        while(true){
            if(i >= 2) {
                System.out.println("Do you want to add one more Answer (\"yes\" or \"no\")?");
                String command = in.nextLine();
                if(command.equals("no")) break;
            }
            answers.add(inputAnswer(in, question, i+1));
            i++;
        }

        question.setAnswers(answers);
        return question;
    }

    public static Answer inputAnswer(Scanner in, Question assignQuestion, int currentNumber){
        Question question = assignQuestion;  // reference to the Question to which the Answer belongs;
        String text;      // string 2 - 150 characters long, supporting Markdown syntax;
        String picture;   // (optional) - if the Answer includes picture, valid URL;
        int score = 0;     // integer number (could be negative too);

        System.out.println("<- Create new Answer (№" + currentNumber + ") ->");
        do {
            System.out.println("Enter text: ");
            text = in.nextLine();
        } while(text.length() < 2 || text.length() > 150);

        System.out.println("Picture: ");
        picture = in.nextLine();

        System.out.println("Score: ");
        score = Integer.parseInt(in.nextLine());

        return new Answer(question, text, picture, score);
    }

    private static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pattern.matcher(email).matches();
    }

    private static boolean isValidPassword(String password){
        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[*.!@$%^&(){}]:;<>,.?/~_+-=|).{8,32}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        if (password == null)
            return false;
        return pattern.matcher(password).matches();
    }

    public static List<Long> inputQuizGame(Scanner in, Quiz quiz) {
        List<Long> correctAnswersAssumptionIds = new ArrayList<>();
        List<Question> questions = quiz.getQuestions();
        System.out.println("==========================================");
        System.out.println("======= WELCOME TO QUIZ: =================");
        System.out.println("  " + quiz.getTitle());
        System.out.println("==========================================");
        questions.forEach(q -> {
            System.out.println(OutputUtils.formatQuestionPlayView(q));
            System.out.println(OutputUtils.formatAnswersPlayView(q.getAnswers()));
            System.out.println("Enter ID (Correct Answer): ");
            Long correctAnswerIdInput = Long.parseLong(in.nextLine());
            correctAnswersAssumptionIds.add(correctAnswerIdInput);
        });
        return correctAnswersAssumptionIds;
    }
}
