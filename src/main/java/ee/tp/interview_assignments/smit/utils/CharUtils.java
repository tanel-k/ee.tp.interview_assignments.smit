package ee.tp.interview_assignments.smit.utils;

public class CharUtils {
	private static final int ASCII_UCASE_UBOUND = 65;
	private static final int ASCII_UCASE_LBOUND = 90;

	public static boolean isAsciiUppercase(char c) {
		// More efficient compared to Character.isUpperCase() in this context.
		return ASCII_UCASE_UBOUND <= c && c <= ASCII_UCASE_LBOUND;
	}
}
