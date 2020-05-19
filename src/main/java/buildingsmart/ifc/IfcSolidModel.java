package buildingsmart.ifc;

import java.util.Objects;

/**
 * A solid model is a complete representation of the nominal shape of a product
 * such that all points in the interior are connected. Any point can be
 * classified as being inside, outside, or on the boundary of a solid. There are
 * several different types of solid model representations.
 */
public abstract class IfcSolidModel extends IfcGeometricRepresentationItem {
    private final IfcDimensionCount dim = new IfcDimensionCount((byte) 3);
    // inverse attribute

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
        IfcSolidModel that = (IfcSolidModel) o;
        return dim.equals(that.dim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dim);
    }
}
