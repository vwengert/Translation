package de.learnlanguage.translation.Vocabular;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TranslationServiceImplTest {

    TranslationServiceImpl translationService;
    @Mock
    private TranslationRepository translationRepository;

    List<Translation> list;

    @BeforeEach
    void setUp() {
        translationRepository = mock(TranslationRepository.class);
        translationService = new TranslationServiceImpl(translationRepository);

        list = new ArrayList<>();
        list.add(new Translation(1L, "1", "1"));
        list.add(new Translation(2L, "2", "2"));
    }

    @Test
    void getTranslationsReturnsListOfTwo() {
        when(translationRepository.findAll()).thenReturn(list);

        List<Translation> translationList = translationService.getTranslations();
        assertEquals(2, translationList.size());
    }

    @Test
    void addTranslationWillGetReturnsListOfThree() {
        list.add(new Translation("3", "3"));
        when(translationRepository.findAll()).thenReturn(list);

        translationService.addNewTranslation(new Translation( "third", "dritte" ));
        List<Translation> translationList = translationService.getTranslations();
        assertEquals(3, translationList.size());
    }

    @Test
    void deleteTranslationWillGetReturnListOfOne() {
        list.remove(1);
        when(translationRepository.existsById(1L)).thenReturn(true);
        when(translationRepository.findAll()).thenReturn(list);

        translationService.deleteTranslation(1L);
        List<Translation> translationList = translationService.getTranslations();
        assertEquals(1, translationList.size());
    }


}