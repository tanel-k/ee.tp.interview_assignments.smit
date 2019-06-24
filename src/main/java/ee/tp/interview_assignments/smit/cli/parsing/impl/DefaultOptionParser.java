package ee.tp.interview_assignments.smit.cli.parsing.impl;

import ee.tp.interview_assignments.smit.cli.parsing.CommandLineOptionParser;

public class DefaultOptionParser implements CommandLineOptionParser<String> {
	@Override
	public String parse(String input) {
		return input;
	}
}
