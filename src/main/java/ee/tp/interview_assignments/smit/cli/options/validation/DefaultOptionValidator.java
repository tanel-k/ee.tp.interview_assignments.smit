package ee.tp.interview_assignments.smit.cli.options.validation;

import ee.tp.interview_assignments.smit.cli.options.CommandLineOptionsBean;

public class DefaultOptionValidator implements CommandLineOptionValidator<Object, CommandLineOptionsBean> {
	@Override
	public void validate(Object option, CommandLineOptionsBean bean) {
		// Do nothing (valid by default).
	}
}
