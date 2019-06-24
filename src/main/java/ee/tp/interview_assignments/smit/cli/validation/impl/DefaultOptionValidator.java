package ee.tp.interview_assignments.smit.cli.validation.impl;

import ee.tp.interview_assignments.smit.cli.validation.InvalidOptionException;
import ee.tp.interview_assignments.smit.cli.validation.OptionValidator;

public final class DefaultOptionValidator implements OptionValidator<Object> {
	@Override
	public void validate(Object optionValue) throws InvalidOptionException {
		// Do nothing (valid by default).
	}
}
