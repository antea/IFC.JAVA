package buildingsmart.ifc;

import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * A label is the term by which something may be referred to. It is a string
 * which represents the human-interpretable name of something and shall have a
 * natural-language meaning.
 */
public class IfcLabel implements IfcDefinedType, IfcSimpleValue {
    private final String value;

    /**
     * @param value Restricted to max. 255 characters, cannot be null.
     * @throws IllegalArgumentException If value is null or longer than 255
     *                                  characters.
     */
    public IfcLabel(@NotNull String value) {
        if (value == null) {
            throw new IllegalArgumentException("ifcLabel cannot be null");
        }
        if (value.length() > 255) {
            throw new IllegalArgumentException(
                    "ifcLabel cannot be longer " + "than 255 characters");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcLabel that = (IfcLabel) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String serialize() {
        return "'" + value + "'";
    }
}
