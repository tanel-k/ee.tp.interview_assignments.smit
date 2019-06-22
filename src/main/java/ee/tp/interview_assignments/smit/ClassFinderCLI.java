package ee.tp.interview_assignments.smit;

import ee.tp.interview_assignments.smit.cli.CommandLineOptionParser;
import ee.tp.interview_assignments.smit.cli.CommandLineParseException;
import ee.tp.interview_assignments.smit.names.ClassName;
import ee.tp.interview_assignments.smit.matching.ClassNameMatcher;
import ee.tp.interview_assignments.smit.matching.QueryParser;
import ee.tp.interview_assignments.smit.utils.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.out;
import static java.lang.System.err;
import static java.lang.System.exit;

public class ClassFinderCLI {
	private enum StatusCode {
		INVALID_INPUT(1, "Invalid input"),
		FILE_IO(2, "File I/O error"),
		UNEXPECTED(3, "Unexpected error");

		private int status;
		private String description;

		StatusCode(int status, String description) {
			this.status = status;
			this.description = description;
		}
	}

	private static void exitWithError(StatusCode statusCode, String detailMessage) {
		exitWithError(statusCode, detailMessage, null);
	}

	private static void exitWithError(StatusCode statusCode, String detailMessage, Throwable t) {
		err.println(
			statusCode.description
				+ (StringUtils.isEmpty(detailMessage) ? "" : ": " + detailMessage)
				+ "."
		);

		if (t != null) {
			err.println("\nStack trace:");
			t.printStackTrace(err);
		}

		exit(statusCode.status);
	}

	public static void main(String... args) {
		CommandLineOptionParser<ClassFinderOptions> parser = CommandLineOptionParser
			.forBean(ClassFinderOptions.class);

		ClassFinderOptions options = null;
		try {
			options = parser.parse(args);

			if (options.getQuery().isEmpty()) {
				exitWithError(StatusCode.INVALID_INPUT,"query should not be empty");
			}

			if (!options.getNameListFile().exists()) {
				exitWithError(StatusCode.INVALID_INPUT, "'" + options.getNameListFile().getPath() + "' does not exist");
			}

			List<String> rawLines = Files.readAllLines(options.getNameListFile().toPath());
			ClassNameMatcher matcher = QueryParser.parse(options.getQuery());

			rawLines.stream()
					.map(String::trim)
					.filter(s -> !s.isEmpty())
					.distinct()
					.map(ClassName::of)
					.filter(matcher::matches)
					.sorted(Comparator.comparing(ClassName::getSimpleName))
					.forEach(out::println);
		} catch (IOException ex) {
			exitWithError(StatusCode.FILE_IO, "failed to read '" + options.getNameListFile().getPath() + "'.", ex);
		} catch (CommandLineParseException ex) {
			exitWithError(StatusCode.INVALID_INPUT, ex.getMessage());
		} catch (Throwable t) {
			exitWithError(StatusCode.UNEXPECTED, "unexpected error", t);
		}
	}
}
