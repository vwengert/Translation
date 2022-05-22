package de.learnlanguage.translation.Vocabular.Service;

import de.learnlanguage.translation.Vocabular.Model.Translation;
import de.learnlanguage.translation.Vocabular.Repository.TranslationRepository;
import de.learnlanguage.translation.Vocabular.util.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TranslationServiceImplTest {

    private final TranslationRepository translationRepository = mock(TranslationRepository.class);
    List<Translation> list;
    TranslationServiceImpl translationService = new TranslationServiceImpl(translationRepository);

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
        list.add(new Translation(1L, "1", "1"));
        list.add(new Translation(2L, "2", "2"));
    }

    @UnitTest
    void getTranslationsReturnsListOfTwo() {
        when(translationRepository.findAll()).thenReturn(list);

        List<Translation> translationList = translationService.getTranslations();

        assertEquals(2, translationList.size());
    }

    @UnitTest
    void addTranslationWillGetReturnsListOfThree() {
        list.add(new Translation("3", "3"));
        when(translationRepository.findAll()).thenReturn(list);

        translationService.addNewTranslation(new Translation("third", "dritte"));
        List<Translation> translationList = translationService.getTranslations();

        assertEquals(3, translationList.size());
    }

    @UnitTest
    void addTranslationWillThrowWhenWordIsTaken() {
        when(translationRepository.findTranslationByWord("1")).thenReturn(Optional.of(new Translation("1", "1")));
        assertThrows(ResponseStatusException.class,
                () -> translationService.addNewTranslation(new Translation("1", "1")));
    }

    @UnitTest
    void deleteTranslationWillGetReturnListOfOne() {
        list.remove(1);
        when(translationRepository.existsById(1L)).thenReturn(true);
        when(translationRepository.findAll()).thenReturn(list);

        translationService.deleteTranslation(1L);
        List<Translation> translationList = translationService.getTranslations();

        assertEquals(1, translationList.size());
    }

    @UnitTest
    void throwsOnDeleteWhenIdIsNotThere() {
        when(translationRepository.existsById(3L)).thenReturn(false);

        Assertions.assertThrows(ResponseStatusException.class, () -> translationService.deleteTranslation(3L));
    }

    @UnitTest
    void throwsWhenUpdateIsOnWrongId() {
        when(translationRepository.findById(3L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> translationService.updateTranslation(3L, "3", "3"));
    }

    @UnitTest
    void updatesTranslationWordOnly() {
        when(translationRepository.findById(1L)).thenReturn(Optional.of(new Translation("1", "1")));

        assertDoesNotThrow(() -> translationService.updateTranslation(1L, "change", null));
    }

    @UnitTest
    void updatesTranslationTranslationOnly() {
        when(translationRepository.findById(1L)).thenReturn(Optional.of(new Translation("1", "1")));

        assertDoesNotThrow(() -> translationService.updateTranslation(1L, null, "change"));
    }

}