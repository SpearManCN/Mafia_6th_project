package mafia;

import mafia.Config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(SecurityConfig.class)
@SpringBootApplication
public class MafiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MafiaApplication.class, args);
    }

}
