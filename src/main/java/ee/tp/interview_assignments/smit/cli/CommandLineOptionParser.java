package ee.tp.interview_assignments.smit.cli;

public class CommandLineOptionParser<T> {
	public static <T> CommandLineOptionParser<T> forBean(Class<T> optionSpec) {
		return new CommandLineOptionParser<>(optionSpec);
	}

	private final Class<T> optionClass;

	private CommandLineOptionParser(Class<T> optionClass) {
		this.optionClass = optionClass;
	}

	public T parse(String... args) throws CommandLineParseException {
		throw new UnsupportedOperationException();
	}
}
