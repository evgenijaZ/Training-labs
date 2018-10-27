package edu.training.loginform;

import edu.training.loginform.model.User;
import edu.training.loginform.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new User("Bilbo", "Baggins")));
            log.info("Preloading " + repository.save(new User("Frodo", "Baggins")));
        };
    }
}