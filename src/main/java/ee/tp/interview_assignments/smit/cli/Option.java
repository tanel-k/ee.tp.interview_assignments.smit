package ee.tp.interview_assignments.smit.cli;

import ee.tp.interview_assignments.smit.cli.parsing.CommandLineOptionParser;
import ee.tp.interview_assignments.smit.cli.parsing.impl.DefaultOptionParser;
import ee.tp.interview_assignments.smit.cli.validation.OptionValidator;
import ee.tp.interview_assignments.smit.cli.validation.impl.DefaultOptionValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Option {
	boolean helpOption() default false;
	boolean required() default true;

	String name();
	String[] aliases() default "";
	String description() default "";

	Class<? extends OptionValidator> validator() default DefaultOptionValidator.class;
	Class<? extends CommandLineOptionParser> parser() default DefaultOptionParser.class;
}
