package ee.tp.interview_assignments.smit.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link StringUtils}.
 */
public class StringUtilsTests {
    @Test
    public void testStartsWith() {
        assertThrows(
            NullPointerException.class,
            () -> StringUtils.startsWith("string", null, null),
            "Rejects null prefix."
        );

        assertThrows(
            NullPointerException.class,
            () -> StringUtils.startsWith(null, "prefix", null),
            "Rejects null string."
        );

        assertEquals(StringUtils.startsWith("string", "str", null), Boolean.TRUE, "Handles null wildcard.");
        assertEquals(StringUtils.startsWith("string", "*t*", '*'), Boolean.TRUE, "Handles wildcard.");
        assertEquals(StringUtils.startsWith("string", "", null), Boolean.TRUE, "Empty string prefixes all strings.");
    }

    @Test
    public void testContainsUppercaseLetters() {
        assertEquals(StringUtils.containsUppercaseLetters("0123459789"), Boolean.FALSE, "Handles numerals.");
        assertEquals(StringUtils.containsUppercaseLetters("a \nb"), Boolean.FALSE, "Handles whitespace.");
        assertEquals(StringUtils.containsUppercaseLetters("abc"), Boolean.FALSE, "Does not return true for lowercase string.");
        assertEquals(StringUtils.containsUppercaseLetters(" aBc"), Boolean.TRUE, "Finds nested uppercase letter.");
        assertEquals(StringUtils.containsUppercaseLetters(null), Boolean.FALSE, "Handles null.");
        assertEquals(StringUtils.containsUppercaseLetters(""), Boolean.FALSE, "Handles empty.");

    }

    @Test
    public void testIsEmpty() {
        assertEquals(StringUtils.isEmpty("abc"), Boolean.FALSE, "Handles non-empty string.");
        assertEquals(StringUtils.isEmpty(" "), Boolean.FALSE, "Handles whitespace string.");
        assertEquals(StringUtils.isEmpty(""), Boolean.TRUE, "Handles empty string.");
        assertEquals(StringUtils.isEmpty(null), Boolean.TRUE, "Handles null string.");
    }

    @Test
    public void testDefaultString() {
        assertEquals(StringUtils.defaultString("test", "abc"), "test", "Does not default non-empty strings.");
        assertEquals(StringUtils.defaultString("", null), null, "Can default to null.");
        assertEquals(StringUtils.defaultString("", ""), "", "Can default to to empty string.");
        assertEquals(StringUtils.defaultString(null, "test"), "test", "Handles null.");
    }

    @Test
    public void testRepeat() {
        assertEquals(StringUtils.repeat("a", 4), "aaaa", "Repeats single character.");
        assertEquals(StringUtils.repeat("str", 3), "strstrstr", "Repeats character sequence.");
        assertEquals(StringUtils.repeat("", 100), "", "Handles empty string.");
        assertEquals(StringUtils.repeat(null, 5), null, "Handles null.");
    }

    @Test
    public void testCapitalize() {
        assertEquals(StringUtils.capitalize("Test"), "Test", "Handles capitalized strings.");
        assertEquals(StringUtils.capitalize("test"), "Test", "Handles uncapitalized strings.");
        assertEquals(StringUtils.capitalize(""), "", "Handles empty string.");
        assertEquals(StringUtils.capitalize(null), null, "Handles null.");
    }

    @Test
    public void testTrimToEmpty() {
        assertEquals(StringUtils.trimToEmpty(" a"), "a", "Trims odd left padding.");
        assertEquals(StringUtils.trimToEmpty("  a"), "a", "Trims even left padding.");
        assertEquals(StringUtils.trimToEmpty("a "), "a", "Trims odd right padding.");
        assertEquals(StringUtils.trimToEmpty("a  "), "a", "Trims even right padding.");
        assertEquals(StringUtils.trimToEmpty(" a  "), "a", "Trims asymmetrical padding.");
        assertEquals(StringUtils.trimToEmpty("  a "), "a", "Trims asymmetrical padding.");
        assertEquals(StringUtils.trimToEmpty("  a  "), "a", "Trims even symmetrical padding.");
        assertEquals(StringUtils.trimToEmpty("   a   "), "a", "Trims odd symmetrical padding.");
        assertEquals(StringUtils.trimToEmpty("  abc  "), "abc", "Retains odd length string.");
        assertEquals(StringUtils.trimToEmpty("  test  "), "test", "Retains even length string.");
        assertEquals(StringUtils.trimToEmpty("\n\t "), "", "Trims whitespace to empty.");
        assertEquals(StringUtils.trimToEmpty(""), "", "Handles empty string.");
        assertEquals(StringUtils.trimToEmpty(null), "", "Trims null to empty.");
    }
}
