package ee.tp.interview_assignments.smit.cli.options.parsing;

public class DefaultOptionParser implements CommandLineOptionParser<String> {
	@Override
	public String parse(String input) {
		return input;
	}
}
