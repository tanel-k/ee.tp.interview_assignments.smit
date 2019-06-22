package ee.tp.interview_assignments.smit.matching;

import ee.tp.interview_assignments.smit.names.ClassName;

public interface ClassNameMatcher {
	char WILDCARD = '*';

	boolean matches(ClassName name);
}
