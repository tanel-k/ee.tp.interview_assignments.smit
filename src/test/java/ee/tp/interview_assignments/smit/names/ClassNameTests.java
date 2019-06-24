package ee.tp.interview_assignments.smit.names;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link ClassName}.
 */
public class ClassNameTests {
    @Test
    public void testExtractSimpleNameFromUnqualifiedClass() {
        assertEquals(
            ClassName.of("Class").getSimpleName(),
            "Class",
            "Correctly extracts simple name from unqualified class name."
        );
    }

    @Test
    public void testExtractSimpleNameFromQualifiedClass() {
        assertEquals(
            ClassName.of("some.qualifier.path.Class").getSimpleName(),
            "Class",
            "Correctly extracts simple name from fully qualified class name."
        );
    }

    @Test
    public void testExtractSimpleNameFromSpecialClass() {
        assertEquals(
            ClassName.of("some.qualifier.path.$_class").getSimpleName(),
            "$_class",
            "Correctly extracts simple name from class name that uses special symbols."
        );
    }

    @Test
    public void testRejectsNullClass() {
        assertThrows(NullPointerException.class, () -> ClassName.of(null), "Rejects null class names.");
    }

    @Test
    public void testRejectsEmptyClass() {
        assertThrows(IllegalArgumentException.class, () -> ClassName.of("\t   \t "), "Rejects empty class names.");
    }
}
