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
        List<String> tokens = new ArrayList<>();
        if (!str.isEmpty()) {
            new CamelCaseIterator(str).forEachRemaining(tokens::add);
        }

        return tokens;
    }

    private final String str;
    private List<String> cachedTokenList;

    public CamelCaseTokenizer(String str) {
        Objects.requireNonNull(str);
        this.str = StringUtils.removeAllWhitespace(str);
    }

    public List<String> getTokenList() {
        if (cachedTokenList == null)
            cachedTokenList = Collections.unmodifiableList(extractTokens(this.str));
        return cachedTokenList;
    }

    @Override
    public Iterator<String> iterator() {
        return getTokenList().iterator();
    }
}
