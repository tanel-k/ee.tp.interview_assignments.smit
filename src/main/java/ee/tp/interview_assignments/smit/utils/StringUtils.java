package ee.tp.interview_assignments.smit.utils;

public class StringUtils {
	public static boolean containsAsciiUppercase(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (CharUtils.isAsciiUppercase(str.charAt(i)))
				return true;
		}
		return false;
	}

	public static String stripDelimiters(String str, char delimiter) {
		if (str.isEmpty())
			return str;

		if (str.length() == 1)
			return str.charAt(0) == delimiter ? "" : str;

		int startIndex = 0;
		int endIndex = str.length();

		if (str.charAt(0) == delimiter)
			startIndex = 1;

		if (str.charAt(str.length() - 1) == delimiter)
			endIndex = str.length() - 1;

		return startIndex != 0 || endIndex != str.length()
				? str.substring(startIndex, endIndex)
				: str;
	}

	public static boolean startsWith(String str, String subStr, char wildcard) {
		if (str.length() < subStr.length())
			return false;

		for (int i = 0; i < subStr.length(); i++) {
			if (subStr.charAt(i) != str.charAt(i) && subStr.charAt(i) != wildcard)
				return false;
		}

		return true;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}
}
