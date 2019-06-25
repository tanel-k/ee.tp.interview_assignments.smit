package ee.tp.interview_assignments.smit.camel_case;

import ee.tp.interview_assignments.smit.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Token extractor for camel-case strings based on {@link CamelCaseIterator}.
 */
public class CamelCaseTokenizer implements Iterable<String> {
    private static List<String> extractTokens(String str) {
        str = StringUtils.removeAllWhitespace(str);

        List<String> tokens = new ArrayList<>();
        if (!str.isEmpty()) {
            new CamelCaseIterator(str).forEachRemaining(tokens::add);
        }

        return tokens;
    }

    private final List<String> immutableTokenList;

    public CamelCaseTokenizer(String str) {
        Objects.requireNonNull(str);
        this.immutableTokenList = Collections.unmodifiableList(extractTokens(str));
    }

    public List<String> getTokenList() {
        return immutableTokenList;
    }

    @Override
    public Iterator<String> iterator() {
        return this.immutableTokenList.iterator();
    }
}
