package de.learnlanguage.translation.Vocabular.Model;

import de.learnlanguage.translation.Vocabular.util.UnitTest;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class TranslationTest {

    Translation translation;

    @BeforeEach
    void setUp() {
        translation = new Translation(1L, "word", "translation");
    }

    @UnitTest
    void testToString() {
        assertEquals("Translation{id=1, word='word', translation='translation'}", translation.toString());
    }

    @UnitTest
    void testEqualsFalseOnNull() {
        assertNull(translation);
    }

    @UnitTest
    void testEqualsTrueIfObjectEqualsItself() {
        assertEquals(translation, translation);
    }

    @UnitTest
    void testEqualsFalseIfObjectNotTranslationObject() {
        assertNotEquals("noTranslationObject", translation);
    }

}