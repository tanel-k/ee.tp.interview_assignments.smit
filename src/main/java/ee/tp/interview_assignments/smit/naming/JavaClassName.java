package ee.tp.interview_assignments.smit.naming;

import java.util.Objects;

public class JavaClassName {
	private static final char NAME_SEPARATOR = '.';

	public static JavaClassName of(String name) {
		return new JavaClassName(name);
	}

	private final String nameString;
	private String simpleName;

	private JavaClassName(String nameString) {
		this.nameString = nameString;
	}

	public String getSimpleName() {
		if (simpleName == null) {
			int lastSepIdx = nameString.lastIndexOf(NAME_SEPARATOR);
			simpleName = lastSepIdx >= 0
				? nameString.substring(lastSepIdx + 1)
				: nameString;
		}
		return simpleName;
	}

	@Override
	public String toString() {
		return nameString;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nameString);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof JavaClassName))
			return false;
		JavaClassName other = (JavaClassName) obj;
		return Objects.equals(this.nameString, other.nameString);
	}
}
