package ee.tp.interview_assignments.smit.matching;

import ee.tp.interview_assignments.smit.class_names.QualifiedClassName;

public interface ClassNameMatcher {
	char WILDCARD = '*';

	boolean matches(QualifiedClassName name);
}
