package ee.tp.interview_assignments.smit.cli.options;

public interface OptionParser<T> {
	T parse(String input) throws InvalidOptionException;
}
