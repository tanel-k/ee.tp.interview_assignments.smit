package ee.tp.interview_assignments.smit.matching;

import ee.tp.interview_assignments.smit.matching.impl.CaseInsensitiveMatcher;

public class MatcherFactory {
	public static ClassNameMatcher parseQuery(String queryString) {
		// TODO: other matchers
		queryString = queryString.replace("\'", "");
		return new CaseInsensitiveMatcher(queryString);
	}

	private MatcherFactory() { }
}
