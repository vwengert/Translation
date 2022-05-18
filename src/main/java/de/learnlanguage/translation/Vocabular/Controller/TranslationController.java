package de.learnlanguage.translation.Vocabular.Controller;

import de.learnlanguage.translation.Vocabular.Model.Translation;
import de.learnlanguage.translation.Vocabular.Service.TranslationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/translation")
@AllArgsConstructor
public class TranslationController {

    private final TranslationService translationService;

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
