package ee.tp.interview_assignments.smit.cli.options.parsers;

import ee.tp.interview_assignments.smit.cli.options.OptionParser;

import java.io.File;

public class FilePathParser implements OptionParser<File> {
	@Override
	public File parse(String input) {
		return new File(input);
	}
}
