package ee.tp.interview_assignments.smit.matching;

import ee.tp.interview_assignments.smit.matching.impl.CaseInsensitiveMatcher;
import ee.tp.interview_assignments.smit.matching.impl.PrefixMatcher;
import ee.tp.interview_assignments.smit.utils.StringUtils;

import java.util.Objects;

/**
 * Parser for class name query strings.
 *
 * @see ClassNameMatcher
 */
public class QueryParser {
    private static final char EXACT_MATCH_MODIFIER = ' ';

    public static ClassNameMatcher parse(String queryString) {
        Objects.requireNonNull(queryString);

        // Note: user is responsible for ensuring their query is "valid".
        // No reason to excessively restrict set of possible queries.
        boolean hasExactMatchModifier = queryString.charAt(queryString.length() - 1) == EXACT_MATCH_MODIFIER;
        boolean hasUppercaseLetters = StringUtils.containsUppercaseLetters(queryString);

        if (hasUppercaseLetters) {
            return new PrefixMatcher(
                hasExactMatchModifier
                    ? queryString.substring(0, queryString.length() - 1)
                    : queryString,
                hasExactMatchModifier
            );
        }

        return new CaseInsensitiveMatcher(queryString);
    }

    private QueryParser() { }
}
