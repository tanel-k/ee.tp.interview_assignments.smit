package ee.tp.interview_assignments.smit.utils;

public class StringUtils {
    public static final String EMPTY_STRING = "";

    /**
     * @return true, if <code>str</code> starts with the sub-string <code>prefix</code>,
     * with <code>wildcard</code> marking positions in <code>str</code> that should be ignored.
     */
    public static boolean startsWith(String str, String prefix, char wildcard) {
        if (str.length() < prefix.length())
            return false;

        for (int i = 0; i < prefix.length(); i++) {
            if (prefix.charAt(i) != str.charAt(i) && prefix.charAt(i) != wildcard)
                return false;
        }

        return true;
    }

    /**
     * @return true, if the input string is <code>null</code> or empty.
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * @return true if the input string contains at least one uppercase letter.
     */
    public static boolean containsUppercaseLetters(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i)))
                return true;
        }
        return false;
    }

    /**
     * @return first argument if it isn't empty, otherwise returns the second argument.
     * @see #isEmpty(String)
     */
    public static String defaultString(String str, String defaultValue) {
        return isEmpty(str) ? defaultValue : str;
    }

    /**
     * @return empty string if argument is empty, otherwise simply returns the argument.
     * @see #isEmpty(String)
     */
    public static String defaultString(String str) {
        return defaultString(str, EMPTY_STRING);
    }

    /**
     * @return string consisting of <code>count</code> copies of <code>str</code>.
     */
    public static String repeat(String str, int count) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < count; i++)
            buf.append(str);
        return buf.toString();
    }

    /**
     * @return capitalized version of the input string.
     */
    public static String capitalize(String str) {
        if (isEmpty(str))
            return str;

        return str.length() == 1
            ? str.toUpperCase()
            : (str.substring(0, 1).toUpperCase() + str.substring(1));
    }

    /**
     * @return pads the input string <code>str</code> with whitespace so that the resultant string is of length <code>width</code>.
     */
    public static String padRight(String str, int width) {
        return String.format("%-" + width + "s", str);
    }

    /**
     * @return trimmed version of the input string.
     */
    public static String trimToEmpty(String str) {
        str = defaultString(str);

        int start = 0;
        while (start < str.length() && (str.charAt(start) == ' ' || str.charAt(start) == '\t'))
            start++;

        int end = str.length() - 1;
        while (end > start && (str.charAt(end) == ' ' || str.charAt(end) == '\t'))
            end--;

        return end + 1 > start ? str.substring(start, end + 1) : EMPTY_STRING;
    }
}
