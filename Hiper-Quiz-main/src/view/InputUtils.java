package view;

import model.Gender;
import model.User;

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

        do {
            System.out.print("Enter username: ");
            username = in.next();
        } while(username.length() < 2 || username.length() > 15);

        do {
            System.out.print("Enter email: ");
            email = in.next();
        } while(!isValidEmail(email));

        do {
            System.out.print("Enter password: ");
            password = in.next();
        } while(!isValidPassword(password));

        String genderStr;
        do {
            System.out.print("Enter gender (MALE/FEMALE): ");
            gender = in.next();
        } while(!gender.equals("MALE") && !gender.equals("FEMALE"));

        do {
            System.out.print("Enter description: ");
            description = in.next();
        } while(description.length() < 20 || description.length() > 250);

        System.out.print("Picture: ");
        picture = in.next();

        do{
            System.out.print("Enter metadata: ");
            metadata = in.next();
        } while(metadata.length() < 0 || metadata.length() > 512);

        User createdUser = new User(username, email, password, Gender.valueOf(gender), picture, description, metadata);
        return createdUser;
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
}
