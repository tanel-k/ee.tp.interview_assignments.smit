package ee.tp.interview_assignments.smit.class_names;

import java.util.Objects;

public class SimpleClassName implements Comparable<SimpleClassName> {
	public static SimpleClassName of(String name) {
		return new SimpleClassName(name);
	}

	private final String name;

	private SimpleClassName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof SimpleClassName))
			return false;
		SimpleClassName other = (SimpleClassName) obj;
		return Objects.equals(this.name, other.name);
	}

	@Override
	public int compareTo(SimpleClassName other) {
		return Objects.compare(this.name, other.name, String::compareTo);
	}
}
