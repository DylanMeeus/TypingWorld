package typingworld.database;

import org.jetbrains.annotations.NotNull;
import org.postgresql.Driver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    @NotNull
    private static Database database = new Database();

    private Database(){
        try {
            var connection = getConnection();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
                            @NotNull final String hashedPassword) {
        return false;
    }

}
