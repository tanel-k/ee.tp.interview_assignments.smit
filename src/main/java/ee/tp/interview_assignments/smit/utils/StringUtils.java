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
}
