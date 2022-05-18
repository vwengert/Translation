package de.learnlanguage.translation.Vocabular;

import de.learnlanguage.translation.Vocabular.Model.Translation;
import de.learnlanguage.translation.Vocabular.Repository.TranslationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TranslationConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(TranslationRepository repository) {
        return args -> {
            Translation hello = new Translation("Hallo", "Hello");
            Translation welt = new Translation("Welt", "World");
            repository.saveAll(List.of(hello, welt));
        };
    }
}
