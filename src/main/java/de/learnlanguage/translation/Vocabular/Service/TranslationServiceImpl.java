package de.learnlanguage.translation.Vocabular.Service;

import de.learnlanguage.translation.Vocabular.Model.Translation;
import de.learnlanguage.translation.Vocabular.Repository.TranslationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Slf4j
@ConditionalOnProperty(
        value = "translation.service.implementation",
        havingValue = "MARIADB",
        matchIfMissing = true)
@Service
@AllArgsConstructor
public class TranslationServiceImpl implements TranslationService {

    private final TranslationRepository translationRepository;

    @Override
    public List<Translation> getTranslations() {
        log.trace("getTranslation");
        translationRepository.doSpezialOperation(); // hier könnte z.B. eine StoredProcedure aufgerufen werden.
        return translationRepository.findAll();
    }

    @Override
    public void addNewTranslation(Translation translation) {
        log.trace("addNewTranslation");
        if (translationRepository.findTranslationByWord(translation.getWord()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Word taken");
        }
        translationRepository.save(translation);
    }

    @Override
    public void deleteTranslation(Long id) {
        log.trace("deleteTranslation");
        if (!translationRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Translation with id " + id + " does not exist");
        }
        translationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateTranslation(Long id, String word, String translationWord) {
        log.trace("updateTranslation");
        Translation translation = translationRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Translation with id " + id + " does not exist"));
        if (word != null && word.length() > 0 && !Objects.equals(translation.getWord(), word)) {
            log.trace("Update changes word");
            translation.setWord(word);
        }
        if (translationWord != null && translationWord.length() > 0 && !Objects.equals(translation.getTranslation(), translationWord)) {
            log.trace("Update changes translation");
            translation.setTranslation(translationWord);
        }
        translationRepository.save(translation);
    }
}
