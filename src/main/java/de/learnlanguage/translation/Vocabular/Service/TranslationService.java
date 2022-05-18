package de.learnlanguage.translation.Vocabular.Service;

import de.learnlanguage.translation.Vocabular.Model.Translation;

import java.util.List;

public interface TranslationService {
    List<Translation> getTranslations();

    void addNewTranslation(Translation translation);

    void deleteTranslation(Long id);

    void updateTranslation(Long id, String word, String translation);
}
