package ee.tp.interview_assignments.smit.cli.options.parsing;

public class CommandLineOptionParseException extends Exception {
	public CommandLineOptionParseException(String msg) {
		super(msg);
	}

	public CommandLineOptionParseException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
