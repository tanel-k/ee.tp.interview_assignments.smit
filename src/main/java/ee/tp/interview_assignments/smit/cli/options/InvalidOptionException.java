package ee.tp.interview_assignments.smit.cli.options;

public class InvalidOptionException extends Exception {
	public InvalidOptionException(String msg) {
		super(msg);
	}

	public InvalidOptionException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
