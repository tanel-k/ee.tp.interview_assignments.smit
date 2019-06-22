package ee.tp.interview_assignments.smit.camel_case;

import ee.tp.interview_assignments.smit.utils.CharUtils;

import java.util.Iterator;

public class CamelCaseIterator implements Iterator<String> {
	private int index;
	private final int length;
	private final String string;

	public CamelCaseIterator(String string) {
		this.index = 0;
		this.string = string;
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
		while (++index < length) {
			if (CharUtils.isAsciiUppercase(string.charAt(index)))
				break;
		}

		return string.substring(startIndex, index);
	}
}
