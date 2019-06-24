package ee.tp.interview_assignments.smit.cli.validation.impl;

import ee.tp.interview_assignments.smit.cli.validation.InvalidOptionException;
import ee.tp.interview_assignments.smit.cli.validation.OptionValidator;

import java.io.File;
import java.nio.file.Files;

public class FileValidator implements OptionValidator<File> {
	@Override
	public void validate(File optionValue) throws InvalidOptionException {
		if (!optionValue.exists())
			throw new InvalidOptionException("File '" + optionValue.getPath() + "' does not exist.");

		if (!Files.isReadable(optionValue.toPath()))
			throw new InvalidOptionException("File '" + optionValue.getPath() + "' is not readable.");
	}
}

