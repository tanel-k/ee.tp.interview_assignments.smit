package ee.tp.interview_assignments.smit.camel_case;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CamelCaseTokenizer implements Iterable<String> {
	private static List<String> extractTokens(String str) {
		List<String> tokens = new ArrayList<>();
		new CamelCaseIterator(str).forEachRemaining(tokens::add);
		return tokens;
	}

	private final List<String> immutableTokenList;

	public CamelCaseTokenizer(String string) {
		this.immutableTokenList = Collections.unmodifiableList(extractTokens(string));
	}

	public List<String> getTokenList() {
		return immutableTokenList;
	}

	@Override
	public Iterator<String> iterator() {
		return this.immutableTokenList.iterator();
	}
}