package de.learnlanguage.translation.Vocabular;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/translation")
@AllArgsConstructor
public class TranslationController {

    private final TranslationService translationService;

    @GetMapping
    public List<Translation> hello() {
        return translationService.getTranslations();
    }

    @PostMapping
    public void registerNewTranslation(@RequestBody Translation translation) {
        translationService.addNewTranslation(translation);
    }

    @DeleteMapping(path = "{translationId}")
    public void deleteTranslation(@PathVariable("translationId") Long id) {
        translationService.deleteTranslation(id);
    }

    @PutMapping(path = "{translationId}")
    public void updateTranslation(
            @PathVariable("translationId") Long translationId,
            @RequestParam(required = false) String word,
            @RequestParam(required = false) String translation) {
        translationService.updateTranslation(translationId, word, translation);
    }


}

interface TranslationService {
    List<Translation> getTranslations();
    void addNewTranslation(Translation translation);
    void deleteTranslation(Long id);
    void updateTranslation(Long id, String word, String translation);
}