package de.learnlanguage.translation.Vocabular;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@ConditionalOnProperty(
        value="translation.service.implementation",
        havingValue = "MARIADB",
        matchIfMissing = true)
@Service
@AllArgsConstructor
public class TranslationServiceImpl implements TranslationService {

    private final TranslationRepository translationRepository;

    @Override
    public List<Translation> getTranslations() {
        System.out.println("Inside Maria Implementation");
        return translationRepository.findAll();
    }

    @Override
    public void addNewTranslation(Translation translation) {
       if( translationRepository.findTranslationByWord(translation.getWord()).isPresent() ) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Word taken");
       }
       translationRepository.save(translation);
    }

    @Override
    public void deleteTranslation(Long id) {
        if( ! translationRepository.existsById(id) ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Translation with id " + id + " does not exist");
        }
        translationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateTranslation(Long id, String word, String translationWord) {
        Translation translation = translationRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Translation with id " + id + " does not exist"));
        if (word != null && word.length() > 0 && !Objects.equals(translation.getWord(), word)) {
            translation.setWord(word);
        }
        if (translationWord != null && translationWord.length() > 0 && !Objects.equals(translation.getTranslation(), translationWord)) {
            translation.setTranslation(translationWord);
        }
        translationRepository.save(translation);
    }
}
