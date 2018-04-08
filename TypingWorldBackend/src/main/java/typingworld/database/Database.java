package typingworld.database;

import org.jetbrains.annotations.NotNull;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import typingworld.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {

    @NotNull
    private static Database database = new Database();
    private Connection connection;

    private Database(){
        try {
            connection = getConnection();
        } catch (URISyntaxException  | SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String databaseUrl = System.getenv("DATABASE_URL");
        String username = null;
        String password = null;
        String dbUrl = null;
        if (databaseUrl != null) {
            URI dbUri = new URI(databaseUrl);
            username = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];
            dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath() + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        }
        if (databaseUrl == null) {
            // use local setup
            var inStream = Database.class.getResourceAsStream("/application.properties");
            var properties = new Properties();
            try {
                properties.load(inStream);
                dbUrl = "jdbc:"+properties.getProperty("spring.datasource.uri");
            } catch (IOException ioe) {
                throw new RuntimeException("Could not load properties!", ioe);
            }
        }
        return DriverManager.getConnection(dbUrl, username, password);
    }

    @NotNull
    public static Database getDatabase(){
        return database;
    }

    public boolean register(@NotNull final String username,
                            @NotNull final String password) {
        try {
            var hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            var statement = connection.prepareStatement("insert into users (username, password) values (?,?)");
            statement.setString(1, username);
            statement.setString(2, hashedPassword);
            statement.execute();
            return true; // if we reach this, everything worked!
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @NotNull
    public List<User> getUsers(){
        var users = new ArrayList<User>();
        try {
            var results = connection.createStatement().executeQuery("select * from users");
            while (results.next()) {
                var username = results.getString("username");
                var firstname = results.getString("firstname");
                var lastname = results.getString("lastname");
                var email = results.getString("email");
                users.add(new User.UserBuilder()
                        .username(username)
                        .firstname(firstname)
                        .lastname(lastname)
                        .email(email)
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
