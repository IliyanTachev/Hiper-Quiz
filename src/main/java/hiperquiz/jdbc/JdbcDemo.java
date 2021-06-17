package hiperquiz.jdbc;

import hiperquiz.entities.Gender;
import hiperquiz.entities.Role;
import hiperquiz.entities.User;
import hiperquiz.view.OutputUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcDemo {
    public static void main(String[] args) throws IOException {
        // 1., load db props from file
        String propsParh = JdbcDemo.class.getClassLoader().getResource("db.properties").getPath();
        Properties props = new Properties();
        props.load(new FileInputStream(propsParh));

        // 2. Load DB driver
        try {
            Class.forName(props.getProperty("driver"));
            System.out.println("DB driver loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: DB driver not found");
            e.printStackTrace();
            return;
        }

        List<User> fetchedUsers = new ArrayList<>();

        // 3. Connect to DB
        try(Connection connection = DriverManager.getConnection(props.getProperty("url"), props)){
            System.out.printf("Successfully connected to: %s", props.getProperty("url"));
            // 4. Create and execute PreparedStatement
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                int fetchedId = resultSet.getInt("id");
                Gender fetchedGender = Gender.values()[resultSet.getInt("gender")];
                Role fetchedRole = Role.values()[resultSet.getInt("role")];
                User fetchedUser = new User(resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("password"), fetchedGender, fetchedRole, resultSet.getString("picture"), resultSet.getString("description"),  resultSet.getString("metadata"), resultSet.getDate("created"), resultSet.getDate("modified"), resultSet.getInt("status") == 1 ? true : false);
                fetchedUser.setId((long) fetchedId);
                fetchedUsers.add(fetchedUser);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(OutputUtils.printAllUsers(fetchedUsers));
    }
}
