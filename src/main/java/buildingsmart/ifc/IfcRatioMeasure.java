package buildingsmart.ifc;

import java.util.Objects;

/**
 * A ratio measure is the value of the relation between two physical quantities
 * that are of the same kind.
 * <p>
 * <BLOCKQUOTE><FONT SIZE="-1">
 * NOTE: Input given in percent is to be divided by 100% when   stored as an
 * IfcRatioMeasure. E.g. 25% becomes 0.25.
 * </FONT>
 * </BLOCKQUOTE>
 */
public class IfcRatioMeasure implements IfcDefinedType, IfcMeasureValue {
    double value;

    public IfcRatioMeasure(double value) {
        this.value = value;
    }

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcRatioMeasure that = (IfcRatioMeasure) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
