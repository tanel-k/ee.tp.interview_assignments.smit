package ee.tp.interview_assignments.smit;

import ee.tp.interview_assignments.smit.matching.MatcherFactory.InvalidQueryException;
import ee.tp.interview_assignments.smit.names.JavaClassName;
import ee.tp.interview_assignments.smit.matching.ClassNameMatcher;
import ee.tp.interview_assignments.smit.matching.MatcherFactory;
import ee.tp.interview_assignments.smit.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.out;
import static java.lang.System.err;

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
		String errorMessage = statusCode.description
				+ (StringUtils.isEmpty(detailMessage) ? "" : ": " + detailMessage)
				+ ".";

		err.println(errorMessage);

		if (t != null) {
			err.println("\nStack trace:");
			t.printStackTrace(err);
		}

		System.exit(statusCode.status);
	}

	public static void main(String... args) {
		// TODO: Proper input parsing.
		if (args.length != 2) {
			exitWithError(StatusCode.INVALID_INPUT, "expected 2, received " + args.length);
		}

		File inputFile = new File(args[0]);
		String query = args[1];

		if (!inputFile.exists()) {
			exitWithError(StatusCode.INVALID_INPUT, "file '" + inputFile.getPath() + "' does not exist");
		}

		try {
			List<String> rawLines = Files.readAllLines(inputFile.toPath());
			ClassNameMatcher matcher = MatcherFactory.parseQuery(StringUtils.stripDelimiters(query, '\''));

			rawLines.stream()
					.map(String::trim)
					.filter(s -> !s.isEmpty())
					.distinct()
					.map(JavaClassName::of)
					.parallel()
					.filter(matcher::matches)
					.sorted(Comparator.comparing(JavaClassName::getSimpleName))
					.forEach(out::println);
		} catch (IOException ex) {
			exitWithError(StatusCode.FILE_IO, "failed to read " + inputFile.getPath() + ".", ex);
		} catch (InvalidQueryException ex) {
			exitWithError(StatusCode.INVALID_INPUT, "invalid query: " + ex.getMessage());
		} catch (Throwable t) {
			exitWithError(StatusCode.UNEXPECTED, "unexpected error", t);
		}
	}
}
