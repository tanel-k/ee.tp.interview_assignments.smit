package ee.tp.interview_assignments.smit.cli;

public class InvalidOptionsException extends Exception {
	public InvalidOptionsException(String msg) {
		super(msg);
	}

	public InvalidOptionsException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
