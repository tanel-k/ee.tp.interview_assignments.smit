package ee.tp.interview_assignments.smit.cli.parsing;

/**
 * Interface for option parsers where the output of the parse operation is of type <code>T</code>.
 *
 * @param <T> output type of the parse operation.
 * @see ee.tp.interview_assignments.smit.cli.Option
 */
public interface OptionParser<T> {
	T parse(String input) throws OptionParseException;
}
