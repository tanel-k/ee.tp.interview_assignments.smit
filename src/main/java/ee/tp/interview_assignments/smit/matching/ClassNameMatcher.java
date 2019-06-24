package ee.tp.interview_assignments.smit.matching;

import ee.tp.interview_assignments.smit.names.ClassName;

/**
 * Interface for class name matching strategies.
 */
public interface ClassNameMatcher {
    char WILDCARD = '*';

    boolean matches(ClassName name);
}
