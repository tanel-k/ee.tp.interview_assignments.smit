package ee.tp.interview_assignments.smit.matching.impl;

import ee.tp.interview_assignments.smit.class_names.QualifiedClassName;
import ee.tp.interview_assignments.smit.matching.ClassNameMatcher;

public class CaseInsensitiveMatcher implements ClassNameMatcher {
	private final String query;

	public CaseInsensitiveMatcher(String query) {
		this.query = query;
	}

	@Override
	public boolean matches(QualifiedClassName name) {
		String simpleName = name.getSimpleName()
				.toString()
				.toLowerCase();

		for (int i = 0, j = 0; i < simpleName.length(); i++) {
			if (query.charAt(j) == WILDCARD || simpleName.charAt(i) == query.charAt(j)) {
				if ((++j) == query.length())
					return true;
			}
		}

		return false;
	}
}
