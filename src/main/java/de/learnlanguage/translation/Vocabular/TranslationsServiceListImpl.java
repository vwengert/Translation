package de.learnlanguage.translation.Vocabular;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@ConditionalOnProperty(
        value="translation.service.implementation",
        havingValue = "LIST",
        matchIfMissing = false)
@Service
@AllArgsConstructor
public class TranslationsServiceListImpl implements TranslationService {

    private static final List<Translation> list = new ArrayList<>();

    {
        list.add(new Translation(1L, "hello", "hello"));
        list.add(new Translation(2L, "welt", "world"));
    }
    @Override
    public List<Translation> getTranslations() {
        return list;
    }

    @Override
    public void addNewTranslation(Translation translation) {
        Long id = 1L;
        for(Translation tr : list) {
            id = tr.getId();
            if(translation.getWord() == tr.getWord()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Word taken");
            }
        }
        translation.setId(id + 1L);
        list.add(translation);
    }

    @Override
    public void deleteTranslation(Long id) {
        for(Translation translation : list) {
            if(translation.getId() == id) {
                list.remove(translation);
                return;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id " + id + " is not in list");
    }

    @Override
    public void updateTranslation(Long id, String word, String translation) {
        for(Translation tr : list) {
            if(tr.getId() == id) {
                tr.setWord(word);
                tr.setTranslation(translation);
                return;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id " + id + " is not in list");
    }
}
