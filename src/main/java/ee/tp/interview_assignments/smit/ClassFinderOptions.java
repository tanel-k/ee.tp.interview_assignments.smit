package ee.tp.interview_assignments.smit;

import ee.tp.interview_assignments.smit.cli.Option;
import ee.tp.interview_assignments.smit.cli.OptionsBean;
import ee.tp.interview_assignments.smit.cli.parsing.impl.FileOptionParser;
import ee.tp.interview_assignments.smit.cli.validation.impl.FileValidator;
import ee.tp.interview_assignments.smit.cli.validation.impl.NonEmptyStringValidator;

import java.io.File;

public class ClassFinderOptions extends OptionsBean {
	@Option(
		name = "--help",
		aliases = "-h",
		description = "Prints usage instructions.",
		helpOption = true
	)
	private boolean printHelp;

	@Option(
		name = "--query",
		aliases = "-q",
		description = "Class name query.",
		validator = NonEmptyStringValidator.class
	)
	private String query;

	@Option(
		name = "--file",
		aliases = "-f",
		description = "Path to file which contains class names.",
		parser = FileOptionParser.class,
		validator = FileValidator.class
	)
	private File nameListFile;

	public boolean doPrintHelp() {
		return printHelp;
	}

	public String getQuery() {
		return query;
	}

	public File getNameListFile() {
		return nameListFile;
	}
}
