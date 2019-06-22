package ee.tp.interview_assignments.smit.matching;

import ee.tp.interview_assignments.smit.matching.impl.CaseInsensitiveMatcher;
import ee.tp.interview_assignments.smit.matching.impl.PrefixMatcher;
import ee.tp.interview_assignments.smit.utils.StringUtils;

public class MatcherFactory {
	private static final char EXACT_MATCH_MODIFIER = ' ';

	public static ClassNameMatcher parseQuery(String queryString) {
		// TODO: VALIDATION

		boolean matchLastExactly = false;
		if (queryString.charAt(queryString.length() - 1) == EXACT_MATCH_MODIFIER) {
			matchLastExactly = true;
			queryString = queryString.substring(0, queryString.length() - 1);
		}

		if (StringUtils.containsAsciiUppercase(queryString))
			return new PrefixMatcher(queryString, matchLastExactly);

		return new CaseInsensitiveMatcher(queryString);
	}

	private MatcherFactory() { }
}
