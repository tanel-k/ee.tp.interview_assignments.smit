package ee.tp.interview_assignments.smit.matching.impl;

import ee.tp.interview_assignments.smit.names.ClassName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link PrefixMatcher}.
 */
public class PrefixMatcherTests {
    @Test
    public void testFindsNestedWord() {
        PrefixMatcher matcher = new PrefixMatcher("Word");
        ClassName className = ClassName.of("FindTheNestedWordOrNot");
        assertEquals(matcher.matches(className), Boolean.TRUE, "Checks nested words in class name.");
    }

    @Test
    public void testChecksLastWordMatch() {
        PrefixMatcher matcher = new PrefixMatcher("TheQuick", true);
        ClassName className = ClassName.of("TheloniousQuickulus");
        assertEquals(matcher.matches(className), Boolean.FALSE, "Checks if last token matches exactly.");

        className = ClassName.of("TheloniousQuick");
        assertEquals(matcher.matches(className), Boolean.TRUE, "Checks if last token matches exactly.");
    }

    @Test
    public void testChecksEachWord() {
        PrefixMatcher matcher = new PrefixMatcher("ThQuBrFo");
        ClassName className = ClassName.of("TheReallyQuickLazyBrownForestFox");
        assertEquals(matcher.matches(className), Boolean.TRUE, "Checks each word in class name.");
    }

    @Test
    public void testChecksCorrectOrder() {
        PrefixMatcher matcher = new PrefixMatcher("TQBFox");
        ClassName className = ClassName.of("FoxBrownTheQuick");
        assertEquals(matcher.matches(className), Boolean.FALSE, "Respects word order specified in query.");
        className = ClassName.of("TheQuickBrownFox");
        assertEquals(matcher.matches(className), Boolean.TRUE, "Respects word order specified in query.");
    }

    @Test
    public void testRespectsWildcard() {
        PrefixMatcher matcher = new PrefixMatcher("Th*Qu***BF**");
        ClassName className = ClassName.of("TheQuickBrownFox");
        assertEquals(matcher.matches(className), Boolean.TRUE, "Respects wildcard semantics.");
    }

    @Test
    public void testIgnoresQualifier() {
        PrefixMatcher matcher = new PrefixMatcher("TQBroFo");
        ClassName className = ClassName.of("The.Quick.Brown.Fox.$gotcha");
        assertEquals(matcher.matches(className), Boolean.FALSE, "Ignores class qualifiers.");
    }
}
