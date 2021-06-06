package view;

import model.User;
import util.PrintUtil;

import java.util.ArrayList;
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

    public static String printAllUsers(List<User> users){
        String userReport = PrintUtil.formatTable(userColumns, users, "Users List:");
        return userReport;
    }
    public static String printUser(User user){
       return printAllUsers(List.of(user));
    }
}
