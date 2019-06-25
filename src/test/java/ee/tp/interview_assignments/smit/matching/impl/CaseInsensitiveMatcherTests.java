package ee.tp.interview_assignments.smit.matching.impl;

import ee.tp.interview_assignments.smit.names.ClassName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link CaseInsensitiveMatcher}.
 */
public class CaseInsensitiveMatcherTests {
    @Test
    public void testOnlyChecksClassName() {
        CaseInsensitiveMatcher matcher = new CaseInsensitiveMatcher("abc");
        ClassName className = ClassName.of("abc.def.Class");
        assertEquals(matcher.matches(className), Boolean.FALSE, "Ignores package string.");
    }

    @Test
    public void testChecksCorrectOrder() {
        CaseInsensitiveMatcher matcher = new CaseInsensitiveMatcher("abc");
        ClassName className = ClassName.of("Acb");
        assertEquals(matcher.matches(className), Boolean.FALSE, "Respects character order specified in query.");

        className = ClassName.of("BxxAxxCxx");
        assertEquals(matcher.matches(className), Boolean.FALSE, "Respects character order specified in query.");

        className = ClassName.of("AxxBxxCxx");
        assertEquals(matcher.matches(className), Boolean.TRUE, "Respects character order specified in query.");
    }

    @Test
    public void testChecksAllWords() {
        CaseInsensitiveMatcher matcher = new CaseInsensitiveMatcher("tqbf");
        ClassName className = ClassName.of("TheQuickBrownFox");
        assertEquals(matcher.matches(className), Boolean.TRUE, "Checks all words in class name.");
    }

    @Test
    public void testChecksWordFully() {
        CaseInsensitiveMatcher matcher = new CaseInsensitiveMatcher("brown");
        ClassName className = ClassName.of("TheQuickBrownFox");
        assertEquals(matcher.matches(className), Boolean.TRUE, "Checks individual words fully.");
    }

    @Test
    public void testChecksFullString() {
        CaseInsensitiveMatcher matcher = new CaseInsensitiveMatcher("thequickbrownfox");
        ClassName className = ClassName.of("TheQuickBrownFox");
        assertEquals(matcher.matches(className), Boolean.TRUE, "Checks full string match.");
    }

    @Test
    public void testRejectsShorterClassName() {
        CaseInsensitiveMatcher matcher = new CaseInsensitiveMatcher("abc");
        ClassName className = ClassName.of("a");
        assertEquals(matcher.matches(className), Boolean.FALSE, "Rejects class names shorter than query.");
    }
}
