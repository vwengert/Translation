package de.learnlanguage.translation.Vocabular.Service;

import de.learnlanguage.translation.Vocabular.Model.Translation;
import de.learnlanguage.translation.Vocabular.util.UnitTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TranslationsServiceListImplTest {


    TranslationsServiceListImpl translationService = new TranslationsServiceListImpl();


    @BeforeEach
    void setUp() {

        translationService.list.add(new Translation(1L, "hello", "hallo"));
        translationService.list.add(new Translation(2L, "world", "welt"));
    }

    @AfterEach
    void tearDown() {
        translationService.list.clear();
    }

    @UnitTest
    void getTranslationsReturnsListOfTwo() {
        List<Translation> translationList = translationService.getTranslations();
        assertEquals(2, translationList.size());
    }

    @UnitTest
    void addTranslationWillGetReturnsListOfThree() {
        translationService.addNewTranslation(new Translation(3L, "what", "was"));
        List<Translation> translationList = translationService.getTranslations();
        assertEquals(3, translationList.size());
    }

    @UnitTest
    void addTranslationWillThrowOnTakenWord() {
        assertThrows(ResponseStatusException.class,
                () -> translationService.addNewTranslation(new Translation("hello", "taken")));
    }

    @UnitTest
    void deleteTranslationWillGetReturnListOfOne() {
        translationService.deleteTranslation(1L);
        List<Translation> translationList = translationService.getTranslations();
        assertEquals(1, translationList.size());
    }

    @UnitTest
    void deleteTranslationWillThrowWhenIdNotFound() {
        assertThrows(ResponseStatusException.class,
                () -> translationService.deleteTranslation(5L));
    }

    @UnitTest
    void updateTranslationsAddsNewTranslation() {
        translationService.updateTranslation(1L, "word", "translation");

        assertEquals("word", translationService.list.get(0).getWord());
        assertEquals("translation", translationService.list.get(0).getTranslation());
    }

    @UnitTest
    void updateTranslationsThrowsWhenTranslationIdIsNotPresent() {
        assertThrows(ResponseStatusException.class,
                () -> translationService.updateTranslation(5L, "not", "nicht"));
    }

}