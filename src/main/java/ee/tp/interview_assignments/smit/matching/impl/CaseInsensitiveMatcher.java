package ee.tp.interview_assignments.smit.matching.impl;

import ee.tp.interview_assignments.smit.matching.ClassNameMatcher;
import ee.tp.interview_assignments.smit.naming.JavaClassName;

public class CaseInsensitiveMatcher implements ClassNameMatcher {
	private final String queryStr;

	public CaseInsensitiveMatcher(String queryStr) {
		this.queryStr = queryStr;
	}

	@Override
	public boolean matches(JavaClassName name) {
		String simpleName = name.getSimpleName().toLowerCase();

		if (simpleName.length() < queryStr.length())
			return false;

		int q = 0;
		char queryChar = queryStr.charAt(q);
		for (int i = 0; i < simpleName.length(); i++) {
			if (queryChar == WILDCARD || simpleName.charAt(i) == queryChar) {
				if ((++q) == queryStr.length())
					return true;
				queryChar = queryStr.charAt(q);
			}
		}

		return false;
	}
}
