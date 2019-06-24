package ee.tp.interview_assignments.smit.cli.parsing.impl;

import ee.tp.interview_assignments.smit.cli.parsing.CommandLineOptionParser;

import java.io.File;

public class FileOptionParser implements CommandLineOptionParser<File> {
	@Override
	public File parse(String input) {
		return new File(input);
	}
}
