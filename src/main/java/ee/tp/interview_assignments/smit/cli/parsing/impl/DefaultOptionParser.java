package ee.tp.interview_assignments.smit.cli.parsing.impl;

import ee.tp.interview_assignments.smit.cli.parsing.OptionParser;

public class DefaultOptionParser implements OptionParser<String> {
	@Override
	public String parse(String input) {
		return input;
	}
}
