package util;

import model.User;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleReader implements InputReader{
    private Scanner scanner = new Scanner(System.in);

    public int readInput(){
        return scanner.nextInt();
    }
    public User readUserDetails(){
            String username = null;
            String email = null;
            String password = null;
            String gender = null;
            String description = null;
            String metadata = null;
            String picture = null;

        do {
            System.out.print("Username: ");
            username = scanner.next();
        } while(username.length() < 2 || username.length() > 15);

        do {
            System.out.print("Email: ");
            email = scanner.next();
        } while(!isValidEmail(email));

        do {
            System.out.print("Password: ");
            password = scanner.next();
        } while(!isValidPassword(password));

        do {
            System.out.print("Gender (MALE/FEMALE): ");
            gender = scanner.next();
        } while(!gender.equals("MALE") && !gender.equals("FEMALE"));

        do {
            System.out.print("Description: ");
            description = scanner.next();
        } while(description.length() < 20 || description.length() > 250);

        System.out.print("Picture: ");
        picture = scanner.next();

        do{
            System.out.print("Metadata: ");
            metadata = scanner.next();
        } while(metadata.length() < 0 || metadata.length() > 512);

        do {
            System.out.print("Status: ");
            gender = scanner.next();
        } while(!gender.equals("true") && !gender.equals("false"));

        return new User(username, email, password, gender, description, metadata, picture);
    }

    public boolean isValidEmail(String email)
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

    public boolean isValidPassword(String password){
        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[*.!@$%^&(){}]:;<>,.?/~_+-=|\\]).{8,15}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        if (password == null)
            return false;
        return pattern.matcher(password).matches();
    }

    public Scanner getScanner() {
        return scanner;
    }
}
