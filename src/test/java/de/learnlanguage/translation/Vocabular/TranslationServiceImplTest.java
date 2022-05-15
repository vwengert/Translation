package de.learnlanguage.translation.Vocabular;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TranslationServiceImplTest {

    @Mock
    private TranslationRepository translationRepository = mock(TranslationRepository.class);
    TranslationServiceImpl translationService = new TranslationServiceImpl(translationRepository);

    List<Translation> list;

    @BeforeEach
    void setUp() {
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

    @Test
    void throwsOnDeleteWhenIdIsNotThere() {
        when(translationRepository.existsById(3L)).thenReturn(false);

        Assertions.assertThrows(ResponseStatusException.class, () -> translationService.deleteTranslation(3L));
    }

    @Test
    void throwsWhenUpdateIsOnWrongId() {
        when(translationRepository.findById(3L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> translationService.updateTranslation(3L, "3", "3"));
    }


}