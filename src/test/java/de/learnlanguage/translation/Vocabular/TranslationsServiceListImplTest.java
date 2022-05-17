package de.learnlanguage.translation.Vocabular;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void getTranslationsReturnsListOfTwo() {
        List<Translation> translationList = translationService.getTranslations();
        assertEquals(2, translationList.size());
    }

    @Test
    void addTranslationWillGetReturnsListOfThree() {
        translationService.addNewTranslation(new Translation(3L, "what", "was"));
        List<Translation> translationList = translationService.getTranslations();
        assertEquals(3, translationList.size());
    }

    @Test
    void addTranslationWillThrowOnTakenWord() {
        assertThrows(ResponseStatusException.class,
                () -> translationService.addNewTranslation(new Translation("hello", "taken")));
    }

    @Test
    void deleteTranslationWillGetReturnListOfOne() {
        translationService.deleteTranslation(1L);
        List<Translation> translationList = translationService.getTranslations();
        assertEquals(1, translationList.size());
    }

    @Test
    void deleteTranslationWillThrowWhenIdNotFound() {
        assertThrows(ResponseStatusException.class,
                () -> translationService.deleteTranslation(5L));
    }

    @Test
    void updateTranslationsAddsNewTranslation() {
        translationService.updateTranslation(1L, "word", "translation");

        assertEquals("word", translationService.list.get(0).getWord());
        assertEquals("translation", translationService.list.get(0).getTranslation());
    }

    @Test
    void updateTranslationsThrowsWhenTranslationIdIsNotPresent() {
        assertThrows(ResponseStatusException.class,
                () -> translationService.updateTranslation(5L, "not", "nicht"));
    }

}