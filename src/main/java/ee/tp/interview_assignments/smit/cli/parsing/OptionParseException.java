package ee.tp.interview_assignments.smit.cli.parsing;

public class OptionParseException extends Exception {
	public OptionParseException(String msg) {
		super(msg);
	}

	public OptionParseException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
