package ee.tp.interview_assignments.smit.cli.validation;

import ee.tp.interview_assignments.smit.cli.OptionsBean;

public interface OptionValidator<T, O extends OptionsBean> {
	void validate(T option, O optionsBean) throws InvalidOptionException;
}
