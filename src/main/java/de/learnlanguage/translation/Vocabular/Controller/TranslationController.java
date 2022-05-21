package de.learnlanguage.translation.Vocabular.Controller;

import de.learnlanguage.translation.Vocabular.Model.Translation;
import de.learnlanguage.translation.Vocabular.Service.TranslationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "api/v1/translation")
@AllArgsConstructor
public class TranslationController {

    private final TranslationService translationService;

    @GetMapping
    public List<Translation> getTranslations() {
        log.trace("GET translation called");
        return translationService.getTranslations();
    }

    @PostMapping
    public void registerNewTranslation(@RequestBody Translation translation) {
        log.trace("POST translation called");
        translationService.addNewTranslation(translation);
    }

    @DeleteMapping(path = "{translationId}")
    public void deleteTranslation(@PathVariable("translationId") Long id) {
        log.trace("DELETE translation called");
        translationService.deleteTranslation(id);
    }

    @PutMapping(path = "{translationId}")
    public void updateTranslation(
            @PathVariable("translationId") Long translationId,
            @RequestParam(required = false) String word,
            @RequestParam(required = false) String translation) {
        log.trace("PUT translation called");
        translationService.updateTranslation(translationId, word, translation);
    }


}
