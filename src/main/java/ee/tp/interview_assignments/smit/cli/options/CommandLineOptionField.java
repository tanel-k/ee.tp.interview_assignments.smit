package ee.tp.interview_assignments.smit.cli.options;

import ee.tp.interview_assignments.smit.cli.options.parsing.CommandLineOptionParser;
import ee.tp.interview_assignments.smit.cli.options.parsing.DefaultOptionParser;
import ee.tp.interview_assignments.smit.cli.options.validation.CommandLineOptionValidator;
import ee.tp.interview_assignments.smit.cli.options.validation.DefaultOptionValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CommandLineOptionField {
	boolean helpOption() default false;
	boolean required() default true;

	String name();
	String[] aliases() default "";
	String description() default "";

	Class<? extends CommandLineOptionValidator> validator() default DefaultOptionValidator.class;
	Class<? extends CommandLineOptionParser> parser() default DefaultOptionParser.class;
}
