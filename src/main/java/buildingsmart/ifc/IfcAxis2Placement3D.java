package buildingsmart.ifc;

import buildingsmart.util.Functions;
import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * The location and orientation in three dimensional space of three mutually
 * perpendicular axes. An axis2_placement_3D is defined in terms of a point
 * (inherited from placement supertype) and two (ideally orthogonal) axes. It
 * can be used to locate and originate an object in three dimensional space and
 * to define a placement coordinate system. The entity includes a point which
 * forms the origin of the placement coordinate system. Two direction vectors
 * are required to complete the definition of the placement coordinate system.
 * The axis is the placement Z axis direction and the ref_direction is an
 * approximation to the placement X axis direction.
 */
public class IfcAxis2Placement3D extends IfcPlacement
        implements IfcAxis2Placement {
    private final IfcDirection axis;
    private final IfcDirection refDirection;
    //private IfcDirection[] p; TODO: calculate this by normalizing axis and
    // refDirection, and then use this in equals()?
    //TODO: test the constructor

    /**
     * @param location     The location of the three mutually perpendicular
     *                     axes.
     * @param axis         The exact direction of the local Z Axis.
     * @param refDirection The direction used to determine the direction of the
     *                     local X Axis. If necessary an adjustment is made to
     *                     maintain orthogonality to the Axis direction. If Axis
     *                     and/or RefDirection is omitted, these directions are
     *                     taken from the geometric coordinate system.
     * @throws IllegalArgumentException If any of the following conditions is
     *                                  not met:
     *                                  <bl>
     *                                  <li>the dimensionality of the placement
     *                                  location shall be 3;</li>
     *                                  <li>the Axis (when given) should only
     *                                  reference a three-dimensional
     *                                  IfcDirection;</li>
     *                                  <li>the RefDirection (when given)
     *                                  should only reference a
     *                                  three-dimensional
     *                                  IfcDirection;</li>
     *                                  <li>the Axis and RefDirection shall not
     *                                  be parallel or anti-parallel;</li>
     *                                  <li>either both (Axis and RefDirection)
     *                                  are not given and therefore defaulted,
     *                                  or both shall be given.</li>
     *                                  </bl>
     */
    public IfcAxis2Placement3D(@NotNull IfcCartesianPoint location,
                               IfcDirection axis, IfcDirection refDirection) {
        super(location);
        if (super.getDim().getValue() != 3) {
            throw new IllegalArgumentException(
                    "location must have dimensionality equal to 3");
        }
        if (axis != null && axis.getDim().getValue() != 3) {
            throw new IllegalArgumentException(
                    "if axis is not null, it must have dimensionality equal " +
                            "to 3");
        }
        if (refDirection != null && refDirection.getDim().getValue() != 3) {
            throw new IllegalArgumentException(
                    "if refDirection is not null, it must have dimensionality" +
                            " equal to 3");
        }
        if (axis != null && refDirection != null &&
                Functions.ifcCrossProduct(axis, refDirection).getMagnitude()
                        .getValue() <= 0) {
            throw new IllegalArgumentException(
                    "axis and refDirection cannot be parallel or " +
                            "anti-parallel");
        }
        if ((axis == null && refDirection != null) ||
                (axis != null && refDirection == null)) {
            throw new IllegalArgumentException(
                    "either both axis and refDirection are set, or none " +
                            "should be set");
        }
        this.axis = axis;
        this.refDirection = refDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        IfcAxis2Placement3D that = (IfcAxis2Placement3D) o;
        return Objects.equals(axis, that.axis) &&
                Objects.equals(refDirection, that.refDirection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), axis, refDirection);
    }
}
