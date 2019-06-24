package ee.tp.interview_assignments.smit.cli.options.parsing;

import java.io.File;

public class FileOptionParser implements CommandLineOptionParser<File> {
	@Override
	public File parse(String input) {
		return new File(input);
	}
}
