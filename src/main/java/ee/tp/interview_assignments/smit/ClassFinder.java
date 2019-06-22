package ee.tp.interview_assignments.smit;

import ee.tp.interview_assignments.smit.query.ClassNameMatcher;
import ee.tp.interview_assignments.smit.query.QueryParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static java.lang.System.out;

public class ClassFinder {
	public static void main(String... args) throws IOException {
		if (args.length < 2) {
			// TODO: Display error.
		}

		// TODO: Proper input parsing.
		File inputFile = new File(args[0]);
		String query = args[1];

		if (!inputFile.exists()) {
			// TODO: Display error.
		}

		List<String> rawLines = Files.readAllLines(inputFile.toPath());
		ClassNameMatcher matcher = QueryParser.parse(query);

		rawLines.stream()
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.distinct()
				.filter(matcher::matches)
				.sorted()
				.forEach(out::println);
	}
}
