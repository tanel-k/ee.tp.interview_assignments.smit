package ee.tp.interview_assignments.smit.matching;

import ee.tp.interview_assignments.smit.names.JavaClassName;

public interface ClassNameMatcher {
	char WILDCARD = '*';

	boolean matches(JavaClassName name);
}
