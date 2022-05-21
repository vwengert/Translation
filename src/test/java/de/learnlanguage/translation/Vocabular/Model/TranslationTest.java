package de.learnlanguage.translation.Vocabular.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TranslationTest {

    Translation translation;

    @BeforeEach
    void setUp() {
        translation = new Translation(1L, "word", "translation");
    }

    @Test
    void testToString() {
        assertEquals("Translation{id=1, word='word', translation='translation'}", translation.toString());
    }

    @Test
    void testEqualsFalseOnNull() {
        assertFalse(translation.equals(null));
    }

    @Test
    void testEqualsTrueIfObjectEqualsItself() {
        assertTrue(translation.equals(translation));
    }

    @Test
    void testEqualsFalseIfObjectNotTranslationObject() {
        assertFalse(translation.equals("noTranslationObject"));
    }

}