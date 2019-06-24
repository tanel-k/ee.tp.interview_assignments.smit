package ee.tp.interview_assignments.smit.camel_case;

import java.util.Iterator;
import java.util.Objects;

/**
 * Token iterator for camel-case strings.<br/>
 * Treats each sub-sequence starting with an uppercase letter as a separate token.
 */
public class CamelCaseIterator implements Iterator<String> {
	private int index;
	private final int length;
	private final String string;

	public CamelCaseIterator(String string) {
		this.index = 0;
		this.string = Objects.requireNonNull(string);
		this.length = string.length();
	}

	@Override
	public boolean hasNext() {
		return index < length;
	}

	@Override
	public String next() {
		int startIndex = index;

		// We assume that it doesn't matter whether the *first* char is lower- or uppercase.
		while (++index < length)
			if (Character.isUpperCase(string.charAt(index)))
				break;

		return string.substring(startIndex, index);
	}
}
