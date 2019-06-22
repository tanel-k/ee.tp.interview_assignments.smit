package ee.tp.interview_assignments.smit.cli.options;

import ee.tp.interview_assignments.smit.cli.options.parsers.DefaultParser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CommandLineOption {
	String name();

	String usage() default "";
	String[] aliasNames() default "";

	boolean required() default true;
	Class<?> parser() default DefaultParser.class;
}
