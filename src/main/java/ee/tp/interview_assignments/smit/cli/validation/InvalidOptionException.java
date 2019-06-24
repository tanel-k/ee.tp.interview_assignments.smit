package ee.tp.interview_assignments.smit.cli.validation;

/**
 * Exception for errors that can be expected during option validation.
 *
 * @see OptionValidator
 */
public class InvalidOptionException extends Exception {
    public InvalidOptionException(String msg) {
        super(msg);
    }

    public InvalidOptionException(String msg, Throwable t) {
        super(msg, t);
    }
}
