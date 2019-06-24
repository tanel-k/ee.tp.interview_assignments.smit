package ee.tp.interview_assignments.smit;

import ee.tp.interview_assignments.smit.cli.Option;
import ee.tp.interview_assignments.smit.cli.OptionsBean;
import ee.tp.interview_assignments.smit.cli.parsing.impl.FileOptionParser;
import ee.tp.interview_assignments.smit.cli.validation.OptionValidator;
import ee.tp.interview_assignments.smit.cli.validation.InvalidOptionException;
import ee.tp.interview_assignments.smit.utils.StringUtils;

import java.io.File;

public class ClassFinderOptions extends OptionsBean {
	private static class ClassListFileValidator implements OptionValidator<File,ClassFinderOptions> {
		@Override
		public void validate(File option, ClassFinderOptions optionsBean) throws InvalidOptionException {
			if (!option.exists())
				throw new InvalidOptionException("'" + option.getPath() + "' does not exist.");
		}
	}

	private static class QueryValidator implements OptionValidator<String, ClassFinderOptions> {
		@Override
		public void validate(String option, ClassFinderOptions optionsBean) throws InvalidOptionException {
			if (StringUtils.isEmpty(option))
				throw new InvalidOptionException("Query cannot be empty.");
		}
	}

	@Option(
		name = "--help",
		aliases = "-h",
		description = "Prints usage instructions.",
		validator = ClassListFileValidator.class,
		helpOption = true
	)
	private boolean printHelp;

	@Option(
		name = "--query",
		aliases = "-q",
		description = "Class name query.",
		validator = QueryValidator.class
	)
	private String query;

	@Option(
		name = "--file",
		aliases = "-f",
		description = "Path to file which contains class names.",
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
