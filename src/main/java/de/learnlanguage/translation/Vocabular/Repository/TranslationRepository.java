package de.learnlanguage.translation.Vocabular.Repository;

import de.learnlanguage.translation.Vocabular.Model.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long>, TranslationRepositoryCustom {
    Optional<Translation> findTranslationByWord(String word);
}

interface TranslationRepositoryCustom {
    void doSpezialOperation();
}
