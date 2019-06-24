package ee.tp.interview_assignments.smit.cli.parsing.impl;

import ee.tp.interview_assignments.smit.cli.parsing.OptionParser;

import java.io.File;

public class FileOptionParser implements OptionParser<File> {
	@Override
	public File parse(String input) {
		return new File(input);
	}
}
