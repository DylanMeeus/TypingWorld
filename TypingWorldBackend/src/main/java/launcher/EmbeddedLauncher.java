package launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Launch the server on tomcat using Spring, rather than starting a war.
 */
@SpringBootApplication
@ComponentScan({"rest.controllers"})
public class EmbeddedLauncher {

    public static void main(String[] args) {
        SpringApplication.run(EmbeddedLauncher.class, args);
    }

}
