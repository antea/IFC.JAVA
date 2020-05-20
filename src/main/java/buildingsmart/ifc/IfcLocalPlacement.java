package buildingsmart.ifc;

import buildingsmart.util.Functions;
import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * The <i>IfcLocalPlacement</i> defines the relative placement of a product in
 * relation to the placement of another product or the absolute placement of a
 * product within the geometric representation context of the project. </p>
 * <p>The <i>IfcLocalPlacement</i> allows that an <i>IfcProduct</i>
 * can be placed by this <i>IfcLocalPlacement</i> (through the
 * attribute<i>ObjectPlacement</i>) within the local coordinate system of the
 * object placement of another <i>IfcProduct</i>, which is referenced by the
 * <i>PlacementRelTo</i>. Rules to prevent cyclic relative placements have to
 * be introduced on the application level.</p>
 * <p>If the <i>PlacementRelTo</i> is not given, then
 * the <i>IfcProduct</i> is placed absolutely within the world coordinate
 * system.</p>
 */
public class IfcLocalPlacement extends IfcObjectPlacement {
    private final IfcObjectPlacement placementRelTo;
    private final IfcAxis2Placement relativePlacement;

    //TODO: test constructor

    /**
     * @param placementRelTo    Reference to Object that provides the relative
     *                          placement by its local coordinate system. If it
     *                          is omitted, then the local placement is given to
     *                          the WCS, established by the project's geometric
     *                          representation context.
     * @param relativePlacement Geometric placement that defines the
     *                          transformation from the related coordinate
     *                          system into the relating. The placement can be
     *                          either 2D or 3D, depending on the dimension
     *                          count of the coordinate system.
     * @throws IllegalArgumentException If relativePlacement is null; if
     *                                  relativePlacement is 3D and
     *                                  placementRelTo is not; if placementRelTo
     *                                  is of type IfcGridPlacement.
     */
    public IfcLocalPlacement(IfcObjectPlacement placementRelTo,
                             @NotNull IfcAxis2Placement relativePlacement) {
        if (relativePlacement == null) {
            throw new IllegalArgumentException(
                    "relativePlacement cannot be null");
        }
        if (!Objects.equals(Functions
                        .ifcCorrectLocalPlacement(relativePlacement,
                                placementRelTo),
                true)) {
            throw new IllegalArgumentException(
                    "if relativePlacement is 3D, so must be placementRelTo; " +
                            "placementRelTo cannot be an IfcGridPlacement");
        }
        this.placementRelTo = placementRelTo;
        this.relativePlacement = relativePlacement;
    }

    public IfcAxis2Placement getRelativePlacement() {
        return relativePlacement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcLocalPlacement that = (IfcLocalPlacement) o;
        return Objects.equals(placementRelTo, that.placementRelTo) &&
                relativePlacement.equals(that.relativePlacement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placementRelTo, relativePlacement);
    }
}
