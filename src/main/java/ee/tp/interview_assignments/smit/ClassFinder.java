package ee.tp.interview_assignments.smit;

import ee.tp.interview_assignments.smit.cli.ArgumentArrayParseException;
import ee.tp.interview_assignments.smit.cli.ArgumentArrayParser;
import ee.tp.interview_assignments.smit.matching.ClassNameMatcher;
import ee.tp.interview_assignments.smit.matching.QueryParser;
import ee.tp.interview_assignments.smit.names.ClassName;
import ee.tp.interview_assignments.smit.utils.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.err;
import static java.lang.System.exit;
import static java.lang.System.out;

/**
 * Entry point for class finder command-line interface.
 */
public class ClassFinder {
    private enum StatusCode {
        INVALID_INPUT(1, "Invalid input."),
        FILE_IO(2, "File I/O error."),
        UNEXPECTED(3, "Unexpected error.");

        private int status;
        private String description;

        StatusCode(int status, String description) {
            this.status = status;
            this.description = description;
        }
    }

    private static void exitWithError(StatusCode statusCode, Throwable t) {
        exitWithError(statusCode, null, t);
    }

    private static void exitWithError(StatusCode statusCode, String detailMessage) {
        exitWithError(statusCode, detailMessage, null);
    }

    private static void exitWithError(StatusCode statusCode, String detailMessage, Throwable t) {
        err.println(statusCode.description);

        if (!StringUtils.isEmpty(detailMessage))
            err.println(detailMessage);

        if (t != null) {
            err.println("\nStack trace:");
            t.printStackTrace(err);
        }

        exit(statusCode.status);
    }

    public static void main(String... args) {
        try {

            ArgumentArrayParser<ClassFinderOptions> parser = ArgumentArrayParser.forClass(
                ClassFinderOptions.class
            );
            ClassFinderOptions options = parser.parse(args);

            if (options.isHelpRequest() || options.doPrintHelp())
                out.println(parser.getHelpMessage());

            if (options.isHelpRequest())
                return;

            List<String> rawLines = Files.readAllLines(options.getNameListFile().toPath());
            ClassNameMatcher matcher = QueryParser.parse(options.getQuery());

            List<ClassName> matchingClassNames = rawLines.stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .distinct()
                .map(ClassName::of)
                .parallel()
                .filter(matcher::matches)
                .collect(Collectors.toList());

            matchingClassNames.stream()
                .sorted(Comparator.comparing(ClassName::getSimpleName))
                .forEach(out::println);

        } catch (ArgumentArrayParseException ex) {
            exitWithError(StatusCode.INVALID_INPUT, ex.getMessage());
        } catch (IOException ex) {
            exitWithError(StatusCode.FILE_IO, ex);
        } catch (Throwable t) {
            exitWithError(StatusCode.UNEXPECTED, t);
        }
    }
}
