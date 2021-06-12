package view;

import model.*;
import util.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.Alignment.*;
import static util.Alignment.CENTER;

public class OutputUtils {
    // Common entity metadata column descriptors
    private static final List<PrintUtil.ColumnDescriptor> metadataColumns = List.of(
            new PrintUtil.ColumnDescriptor("created", "Created", 19, CENTER),
            new PrintUtil.ColumnDescriptor("modified", "Modified", 19, CENTER)
    );

    // Print formatted report as table
    private static final List<PrintUtil.ColumnDescriptor> userColumns = new ArrayList<>(List.of(
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

    private static final List<PrintUtil.ColumnDescriptor> quizColumns = new ArrayList<>(List.of(
                new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
                new PrintUtil.ColumnDescriptor("title", "Title", 22, LEFT),
                new PrintUtil.ColumnDescriptor("author", "Author", 7, CENTER),
                new PrintUtil.ColumnDescriptor("description", "Description", 35, LEFT),
                new PrintUtil.ColumnDescriptor("questions", "Questions", 9, LEFT, 2),
                new PrintUtil.ColumnDescriptor("expectedDuration", "ExpectedDuration", 16, CENTER),
                new PrintUtil.ColumnDescriptor("picture", "Picture", 12, CENTER),
                new PrintUtil.ColumnDescriptor("tags", "Tags", 20, LEFT)

        ));

    private static final List<PrintUtil.ColumnDescriptor> questionColumnsPlayMode = new ArrayList<>(List.of(
            new PrintUtil.ColumnDescriptor("text", "Text", 40, LEFT),
            new PrintUtil.ColumnDescriptor("picture", "Picture", 12, LEFT)
    ));

    private static final List<PrintUtil.ColumnDescriptor> quizResultColumns = new ArrayList<>(List.of(
            new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
            new PrintUtil.ColumnDescriptor("player", "Player", 5, CENTER),
            new PrintUtil.ColumnDescriptor("quiz", "Quiz", 5, CENTER),
            new PrintUtil.ColumnDescriptor("score", "Score", 12, LEFT)
    ));

    private static final List<PrintUtil.ColumnDescriptor> answerColumnsPlayMode = new ArrayList<>(List.of(
            new PrintUtil.ColumnDescriptor("id", "ID", 5, RIGHT),
            new PrintUtil.ColumnDescriptor("text", "Text", 15, LEFT),
            new PrintUtil.ColumnDescriptor("picture", "Picture", 12, LEFT),
            new PrintUtil.ColumnDescriptor("score", "Score", 6, RIGHT, 2)
    ));

    public static String printAllUsers(List<User> users){
        String userReport = PrintUtil.formatTable(userColumns, users, "Users List:");
        return userReport;
    }
    public static String printUser(User user){
       return printAllUsers(List.of(user));
    }

    public static String printAllQuizzes(List<Quiz> quizzes){
        String quizReport = PrintUtil.formatTable(quizColumns, quizzes, "Quizzes List:");
        return quizReport;
    }

    public static String formatQuestionPlayView(Question question){
        String questionReport = PrintUtil.formatTable(questionColumnsPlayMode, Arrays.asList(question), "Questions:");
        return questionReport;
    }

    public static String formatAnswersPlayView(List<Answer> answers){
        String answersReport = PrintUtil.formatTable(answerColumnsPlayMode, answers, "Answers:");
        return answersReport;
    }

    public static String formatQuizResult(QuizResult quizResult){
        String answersReport = PrintUtil.formatTable(quizResultColumns, Arrays.asList(quizResult), "Quiz result:");
        return answersReport;
    }

    public static String formatAllQuizResults(List<QuizResult> quizResults){
        String quizResultReport = PrintUtil.formatTable(quizResultColumns, quizResults, "Results List:");
        return quizResultReport;
    }
}
