package ee.tp.interview_assignments.smit;

import ee.tp.interview_assignments.smit.cli.options.CommandLineOption;
import ee.tp.interview_assignments.smit.cli.options.parsers.FilePathParser;

import java.io.File;

public class ClassFinderOptions {
	@CommandLineOption(
		name = "-q",
		aliasNames = "--query",
		usage = "TODO"
	)
	private String query;
	@CommandLineOption(
		name = "-l",
		aliasNames = "--name-list",
		usage = "TODO",
		parser = FilePathParser.class
	)
	private File nameListFile;

	public File getNameListFile() {
		return nameListFile;
	}

	public String getQuery() {
		return query;
	}
}
