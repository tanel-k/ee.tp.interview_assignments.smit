package ee.tp.interview_assignments.smit.camel_case;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link CamelCaseTokenizer}.
 */
public class CamelCaseTokenizerTests {
    @Test
    public void testRejectsNull() {
        assertThrows(NullPointerException.class, () -> new CamelCaseTokenizer(null));
    }

    @Test
    public void testHandlesEmpty() {
        assertEquals(
            new CamelCaseTokenizer("").getTokenList(),
            Collections.emptyList(),
            "Extracts no tokens from empty string."
        );
    }

    @Test
    public void testHandlesAllWhitespace() {
        assertEquals(
            new CamelCaseTokenizer(" \n\t ").getTokenList(),
            Collections.emptyList(),
            "Extracts no tokens from effectively empty string."
        );
    }

    @Test
    public void testIgnoresWhitespace() {
        assertEquals(
            new CamelCaseTokenizer("Camel Case string").getTokenList(),
            Arrays.asList("Camel", "Casestring"),
            "Ignores all whitespace"
        );
    }

    @Test
    public void testHandlesUncapitalizedString() {
        assertEquals(
            new CamelCaseTokenizer(" camelCase").getTokenList(),
            Arrays.asList("camel", "Case"),
            "Treats first word as separate, even if it starts with a lowercase letter."
        );
    }

    @Test
    public void testHandlesUppercaseSubstring() {
        assertEquals(
            new CamelCaseTokenizer("CamelCASE").getTokenList(),
            Arrays.asList("Camel", "C", "A", "S", "E"),
            "Treats each subsequence starting with an uppercase letter as a separate word."
        );
    }

    @Test
    public void testHandlesNumericalSubstring() {
        assertEquals(
            new CamelCaseTokenizer("ClassNr0123456789").getTokenList(),
            Arrays.asList("Class", "Nr0123456789"),
            "Treats numbers as word parts."
        );
    }

    @Test
    public void testHandlesSpecialSymbols() {
        assertEquals(
            new CamelCaseTokenizer("AsdTest$_sub").getTokenList(),
            Arrays.asList("Asd", "Test$_sub"),
            "Treats special symbols as word parts."
        );
    }
}
