package ee.tp.interview_assignments.smit.cli.options.parsers;

import ee.tp.interview_assignments.smit.cli.options.OptionParser;

public class DefaultParser implements OptionParser<String> {
	@Override
	public String parse(String input) {
		return input;
	}
}
