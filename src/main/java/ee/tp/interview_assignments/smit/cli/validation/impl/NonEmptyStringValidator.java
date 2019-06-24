package ee.tp.interview_assignments.smit.cli.validation.impl;

import ee.tp.interview_assignments.smit.cli.validation.InvalidOptionException;
import ee.tp.interview_assignments.smit.cli.validation.OptionValidator;
import ee.tp.interview_assignments.smit.utils.StringUtils;

/**
 * {@link OptionValidator} implementation for option fields of type {@link String}.<br/>
 * <br/>
 * Ensures string option is not empty.
 */
public class NonEmptyStringValidator implements OptionValidator<String> {
	@Override
	public void validate(String optionValue) throws InvalidOptionException {
		if (StringUtils.isEmpty(optionValue))
			throw new InvalidOptionException("Option cannot be empty.");
	}
}
