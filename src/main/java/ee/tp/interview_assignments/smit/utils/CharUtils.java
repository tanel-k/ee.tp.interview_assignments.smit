package ee.tp.interview_assignments.smit.utils;

public class CharUtils {
	private static final int ASCII_UCASE_UBOUND = 'Z';
	private static final int ASCII_UCASE_LBOUND = 'A';

	private static final int ASCII_LCASE_UBOUND = 'z';
	private static final int ASCII_LCASE_LBOUND = 'a';

	// More efficient compared to Character.isUpperCase() in this context.
	public static boolean isUppercaseAsciiLetter(char ch) {
		return ASCII_UCASE_LBOUND <= ch && ch <= ASCII_UCASE_UBOUND;
	}

	public static boolean isLowercaseAsciiLetter(char ch) {
		return ASCII_LCASE_LBOUND <= ch && ch <= ASCII_LCASE_UBOUND;
	}
}
