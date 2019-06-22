package ee.tp.interview_assignments.smit.query.impl;

import ee.tp.interview_assignments.smit.query.ClassNameMatcher;

public class PrefixMatcher implements ClassNameMatcher {
	private boolean lastSubstringMatchNeeded;

	@Override
	public boolean matches(String className) {
		throw new UnsupportedOperationException();
	}

	public boolean isLastSubstringMatchNeeded() {
		return lastSubstringMatchNeeded;
	}

	public void setLastSubstringMatchNeeded(boolean lastSubstringMatchNeeded) {
		this.lastSubstringMatchNeeded = lastSubstringMatchNeeded;
	}
}
