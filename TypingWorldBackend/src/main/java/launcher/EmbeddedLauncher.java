package launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import typingworld.database.Database;

/**
 * Launch the server on tomcat using Spring, rather than starting a war.
 */
@SpringBootApplication
@ComponentScan({"rest.controllers", "rest.security"})
public class EmbeddedLauncher {

    public static void main(String[] args) {
        var db = Database.getDatabase();
        SpringApplication.run(EmbeddedLauncher.class, args);
    }


}
