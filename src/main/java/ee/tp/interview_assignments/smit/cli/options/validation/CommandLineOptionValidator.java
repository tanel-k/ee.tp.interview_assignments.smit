package ee.tp.interview_assignments.smit.cli.options.validation;

import ee.tp.interview_assignments.smit.cli.options.CommandLineOptionsBean;

public interface CommandLineOptionValidator<T, O extends CommandLineOptionsBean> {
	void validate(T option, O options) throws InvalidOptionException;
}
