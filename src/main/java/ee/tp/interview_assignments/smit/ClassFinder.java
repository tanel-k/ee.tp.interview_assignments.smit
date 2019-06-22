package ee.tp.interview_assignments.smit;

import ee.tp.interview_assignments.smit.naming.JavaClassName;
import ee.tp.interview_assignments.smit.matching.ClassNameMatcher;
import ee.tp.interview_assignments.smit.matching.MatcherFactory;
import ee.tp.interview_assignments.smit.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
		ClassNameMatcher matcher = MatcherFactory.parseQuery(StringUtils.stripDelimiters(query, '\''));

		// TODO: Move this to a separate class:
		rawLines.stream()
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.distinct()
				.map(JavaClassName::of)
				.filter(matcher::matches)
				.sorted(Comparator.comparing(JavaClassName::getSimpleName))
				.forEach(out::println);
	}
}
