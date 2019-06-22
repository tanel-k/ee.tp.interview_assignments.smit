package ee.tp.interview_assignments.smit.matching.impl;

import ee.tp.interview_assignments.smit.class_names.QualifiedClassName;
import ee.tp.interview_assignments.smit.class_names.SimpleClassName;
import ee.tp.interview_assignments.smit.matching.ClassNameMatcher;

public class PrefixMatcher implements ClassNameMatcher {
	private boolean lastSubstringMatchNeeded;
	private final SimpleClassName queryName;

	PrefixMatcher(String query) {
		this.queryName = SimpleClassName.of(query);
	}

	@Override
	public boolean matches(QualifiedClassName name) {
		SimpleClassName simpleName = name.getSimpleName();
		throw new UnsupportedOperationException();
	}
}
