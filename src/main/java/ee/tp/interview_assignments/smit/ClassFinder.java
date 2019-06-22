package ee.tp.interview_assignments.smit;

import ee.tp.interview_assignments.smit.class_names.QualifiedClassName;
import ee.tp.interview_assignments.smit.matching.ClassNameMatcher;
import ee.tp.interview_assignments.smit.matching.MatcherFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
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
		ClassNameMatcher matcher = MatcherFactory.parseQuery(query);

		// TODO: Move this to a separate class:
		rawLines.stream()
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.distinct()
				.map(QualifiedClassName::of)
				.filter(matcher::matches)
				.sorted(Comparator.comparing(QualifiedClassName::getSimpleName))
				.forEach(out::println);
	}
}
