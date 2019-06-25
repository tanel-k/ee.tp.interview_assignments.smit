package ee.tp.interview_assignments.smit.cli.validation;

/**
 * Interface for option validators where the option should be of type <code>T</code>.
 *
 * @param <T> input type for the validator.
 * @see ee.tp.interview_assignments.smit.cli.Option
 */
public interface OptionValidator<T> {
    void validate(T optionValue) throws InvalidOptionException;
}
