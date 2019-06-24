package ee.tp.interview_assignments.smit.utils;

public class StringUtils {
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

	public static boolean containsUppercaseLetters(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (Character.isUpperCase(str.charAt(i)))
				return true;
		}
		return false;
	}

	public static String defaultString(String str, String defaultValue) {
		return isEmpty(str) ? defaultValue : str;
	}

	public static String defaultString(String str) {
		return defaultString(str, "");
	}

	public static String repeat(String str, int count) {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < count; i++)
			buf.append(str);
		return buf.toString();
	}

	public static String capitalize(String str) {
		if (isEmpty(str))
			return str;

		if (str.length() == 1)
			return str.toUpperCase();

		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}
