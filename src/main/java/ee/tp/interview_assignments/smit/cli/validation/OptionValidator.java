package ee.tp.interview_assignments.smit.cli.validation;

public interface OptionValidator<T> {
	void validate(T optionValue) throws InvalidOptionException;
}
