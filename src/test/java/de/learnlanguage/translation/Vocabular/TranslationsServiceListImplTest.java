package de.learnlanguage.translation.Vocabular;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TranslationsServiceListImplTest {


    TranslationsServiceListImpl translationService;

    @BeforeEach
    void setUp() {
        List<Translation> translationList = new ArrayList<>();
        translationList.add(new Translation(1L, "hello", "hallo"));
        translationList.add(new Translation(2L, "world", "welt"));

        translationService = new TranslationsServiceListImpl(translationList);
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