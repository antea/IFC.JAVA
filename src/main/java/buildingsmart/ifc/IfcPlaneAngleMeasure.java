package buildingsmart.ifc;

import java.util.Objects;

/**
 * A plane angle measure is the value of an angle in a plane.
 * <p>
 * Usually measured in radian (rad, m/m = 1), but also grads may be used. The
 * grad unit may be declared as a conversion based unit based on radian unit.
 * <p>
 * <small>NOTE: IfcPlaneAngleMeasure is used where angles need to be described
 * to an
 * accuracy of less than one degree and expressed as decimal parts of an angle.
 * It is widely used for angular measurement except for situations where
 * accuracy needs to be defined using arc measurement; for which purpose the
 * IfcCompoundPlaneAngleMeasure is provided.</small>
 */
public class IfcPlaneAngleMeasure implements IfcDefinedType, IfcMeasureValue {
    private final double value;

    public IfcPlaneAngleMeasure(double value) {
        this.value = value;
    }

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return "IFCPLANEANGLEMEASURE(" + value + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcPlaneAngleMeasure that = (IfcPlaneAngleMeasure) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
