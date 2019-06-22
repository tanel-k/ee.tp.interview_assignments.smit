package ee.tp.interview_assignments.smit.matching;

import ee.tp.interview_assignments.smit.matching.impl.CaseInsensitiveMatcher;
import ee.tp.interview_assignments.smit.matching.impl.PrefixMatcher;
import ee.tp.interview_assignments.smit.utils.CharUtils;

public class MatcherFactory {
	public static class InvalidQueryException extends Exception {
		private InvalidQueryException(String message) {
			super(message);
		}
	}

	private static final char EXACT_MATCH_MODIFIER = ' ';

	public static ClassNameMatcher parseQuery(String queryString) throws InvalidQueryException {
		boolean containsExactMatchModifier = false;
		boolean containsAsciiUppercase = false;

		for (int i = 0; i < queryString.length(); i++) {
			char ch = queryString.charAt(i);

			if (ch == EXACT_MATCH_MODIFIER) {
				if (containsExactMatchModifier = (i == queryString.length() - 1))
					break;

				throw new InvalidQueryException(
						"unexpected exact match modifier '" + EXACT_MATCH_MODIFIER + "' at position " + i
				);
			}

			if (CharUtils.isUppercaseAsciiLetter(ch)) {
				containsAsciiUppercase = true;
				continue;
			}

			if (!CharUtils.isLowercaseAsciiLetter(ch) && ch != ClassNameMatcher.WILDCARD) {
				throw new InvalidQueryException("unexpected character '" + ch + "' at position " + i);
			}
		}

		if (containsAsciiUppercase)
			return new PrefixMatcher(
					containsExactMatchModifier
							? queryString.substring(0, queryString.length() - 1)
							: queryString,
					containsExactMatchModifier
			);

		return new CaseInsensitiveMatcher(queryString);
	}

	private MatcherFactory() { }
}
