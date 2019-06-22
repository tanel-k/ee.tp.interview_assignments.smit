package ee.tp.interview_assignments.smit.class_names;

import java.util.Objects;

public class QualifiedClassName implements Comparable<QualifiedClassName> {
	private static final char CLASS_NAME_SEPARATOR = '.';

	public static QualifiedClassName of(String name) {
		return new QualifiedClassName(name);
	}

	private final String nameString;
	private SimpleClassName simpleName;

	private QualifiedClassName(String nameString) {
		this.nameString = nameString;
	}

	public SimpleClassName getSimpleName() {
		if (simpleName == null) {
			int lastSepIdx = nameString.lastIndexOf(CLASS_NAME_SEPARATOR);
			simpleName = SimpleClassName.of(
					lastSepIdx >= 0
							? nameString.substring(lastSepIdx + 1)
							: nameString
			);
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
		if (!(obj instanceof QualifiedClassName))
			return false;
		QualifiedClassName other = (QualifiedClassName) obj;
		return Objects.equals(this.nameString, other.nameString);
	}

	@Override
	public int compareTo(QualifiedClassName other) {
		return nameString.compareTo(other.nameString);
	}
}
