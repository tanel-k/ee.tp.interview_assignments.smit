package ee.tp.interview_assignments.smit.cli.parsing;

public interface OptionParser<T> {
	T parse(String input) throws OptionParseException;
}
