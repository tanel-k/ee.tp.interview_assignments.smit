package ee.tp.interview_assignments.smit;

import ee.tp.interview_assignments.smit.cli.options.CommandLineOptionField;
import ee.tp.interview_assignments.smit.cli.options.CommandLineOptionsBean;
import ee.tp.interview_assignments.smit.cli.options.parsing.FileOptionParser;
import ee.tp.interview_assignments.smit.cli.options.validation.CommandLineOptionValidator;
import ee.tp.interview_assignments.smit.cli.options.validation.InvalidOptionException;
import ee.tp.interview_assignments.smit.utils.StringUtils;

import java.io.File;

public class ClassFinderCLIOptions extends CommandLineOptionsBean {
	private static class ClassListFileValidator implements CommandLineOptionValidator<File,ClassFinderCLIOptions> {
		@Override
		public void validate(File option, ClassFinderCLIOptions options) throws InvalidOptionException {
			if (!option.exists())
				throw new InvalidOptionException("'" + option.getPath() + "' does not exist.");
		}
	}

	private static class QueryValidator implements CommandLineOptionValidator<String, ClassFinderCLIOptions> {
		@Override
		public void validate(String option, ClassFinderCLIOptions options) throws InvalidOptionException {
			if (StringUtils.isEmpty(option))
				throw new InvalidOptionException("Query should not be empty.");
		}
	}

	@CommandLineOptionField(
		name = "--help",
		aliases = "-h",
		description = "Prints usage instructions.",
		validator = ClassListFileValidator.class,
		helpOption = true
	)
	private boolean printHelp;

	@CommandLineOptionField(
		name = "--query",
		aliases = "-q",
		description = "Class name query.",
		validator = QueryValidator.class
	)
	private String query;

	@CommandLineOptionField(
		name = "--file",
		aliases = "-f",
		description = "Path to file containing class names.",
		parser = FileOptionParser.class
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
