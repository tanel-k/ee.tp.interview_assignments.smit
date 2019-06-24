package ee.tp.interview_assignments.smit.cli.validation.impl;

import ee.tp.interview_assignments.smit.cli.OptionsBean;
import ee.tp.interview_assignments.smit.cli.validation.OptionValidator;

public class DefaultOptionValidator implements OptionValidator<Object, OptionsBean> {
	@Override
	public void validate(Object option, OptionsBean optionsBean) {
		// Do nothing (valid by default).
	}
}
