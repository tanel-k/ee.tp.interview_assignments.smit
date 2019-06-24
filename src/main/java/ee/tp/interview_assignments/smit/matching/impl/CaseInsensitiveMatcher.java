package ee.tp.interview_assignments.smit.matching.impl;

import ee.tp.interview_assignments.smit.matching.ClassNameMatcher;
import ee.tp.interview_assignments.smit.names.ClassName;

import java.util.Objects;

/**
 * {@link ClassNameMatcher} implementation which will match a class name based on whether it contains a sub-sequence
 * of characters specified in the query string (case-insensitive).
 * @see ee.tp.interview_assignments.smit.matching.QueryParser
 */
public class CaseInsensitiveMatcher implements ClassNameMatcher {
	private final String queryStr;

	public CaseInsensitiveMatcher(String queryStr) {
		this.queryStr = Objects.requireNonNull(queryStr);
	}

	@Override
	public boolean matches(ClassName name) {
		String simpleName = name.getSimpleName().toLowerCase();

		if (simpleName.length() < queryStr.length())
			return false;

		for (int i = 0, q = 0; i < simpleName.length(); i++) {
			char queryChar = queryStr.charAt(q);
			if (queryChar == WILDCARD || simpleName.charAt(i) == queryChar) {
				if ((++q) == queryStr.length())
					return true;
			}
		}

		return false;
	}
}
