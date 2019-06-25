package ee.tp.interview_assignments.smit.names;

import ee.tp.interview_assignments.smit.utils.StringUtils;

import java.util.Objects;

/**
 * Wrapper for a class name string.
 */
public class ClassName implements Comparable<ClassName> {
    private static final char NAME_SEPARATOR = '.';

    public static ClassName of(String name) {
        return new ClassName(name);
    }

    private final String nameString;
    private String simpleName;

    private ClassName(String nameString) {
        Objects.requireNonNull(nameString);

        if ((nameString = StringUtils.trimToEmpty(nameString)).isEmpty())
            throw new IllegalArgumentException("Class name cannot be empty.");

        this.nameString = StringUtils.removeAllWhitespace(nameString);
    }

    /**
     * @return the last part of the wrapped (potentially fully qualified) class name
     */
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
        if (!(obj instanceof ClassName))
            return false;
        ClassName other = (ClassName) obj;
        return Objects.equals(this.nameString, other.nameString);
    }

    @Override
    public int compareTo(ClassName other) {
        return this.nameString.compareTo(other.nameString);
    }
}
