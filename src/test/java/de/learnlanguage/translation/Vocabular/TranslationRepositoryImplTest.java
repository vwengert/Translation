package de.learnlanguage.translation.Vocabular;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TranslationRepositoryImplTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void doSpezialOperationOnGenericImpl() {
        RepoBean repoBean = new RepoBeanGenericImpl();
        TranslationRepositoryImpl translationRepository = new TranslationRepositoryImpl(repoBean);
        translationRepository.doSpezialOperation();
        assertEquals("Version Generic", outContent.toString().trim());
    }

    @Test
    void doSpezialOperationOnMariaImpl() {
        RepoBean repoBean = new RepoBeanMariaImpl();
        TranslationRepositoryImpl translationRepository = new TranslationRepositoryImpl(repoBean);
        translationRepository.doSpezialOperation();
        assertEquals("Version Mariadb", outContent.toString().trim());
    }

}