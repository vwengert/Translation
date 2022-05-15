package de.learnlanguage.translation.Vocabular;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void deleteTranslationWillGetReturnListOfOne() {
        translationService.deleteTranslation(1L);
        List<Translation> translationList = translationService.getTranslations();
        assertEquals(1, translationList.size());
    }

}