package de.learnlanguage.translation.Vocabular;

import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@ConditionalOnProperty(
        value="translation.service.implementation",
        havingValue = "LIST")
@Service
@NoArgsConstructor
public class TranslationsServiceListImpl implements TranslationService {

    protected final List<Translation> list = new ArrayList<>();

    @Override
    public List<Translation> getTranslations() {
        return list;
    }

    @Override
    public void addNewTranslation(Translation translation) {
        Long id = 0L;
        for(Translation tr : list) {
            id = tr.getId();
            if(translation.getWord().equals(tr.getWord())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Word taken");
            }
        }
        translation.setId(id + 1L);
        list.add(translation);
    }

    @Override
    public void deleteTranslation(Long id) {
        for(Translation translation : list) {
            if(translation.getId().equals(id)) {
                list.remove(translation);
                return;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id " + id + " is not in list");
    }

    @Override
    public void updateTranslation(Long id, String word, String translation) {
        for(Translation tr : list) {
            if(tr.getId().equals(id)) {
                if(word != null && word.length() > 0) {
                    tr.setWord(word);
                }
                if(translation != null && translation.length() > 0) {
                    tr.setTranslation(translation);
                }
                return;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id " + id + " is not in list");
    }
}
