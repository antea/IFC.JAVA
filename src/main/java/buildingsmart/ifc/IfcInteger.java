package buildingsmart.ifc;

import java.util.Objects;

/**
 * A defined type of simple data type Integer.
 */
public class IfcInteger implements IfcDefinedType {
    private final long value;

    public IfcInteger(long value) {
        this.value = value;
    }

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return Long.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcInteger that = (IfcInteger) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
