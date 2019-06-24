package ee.tp.interview_assignments.smit.cli.options.parsing;

public interface CommandLineOptionParser<T> {
	T parse(String input) throws CommandLineOptionParseException;
}
