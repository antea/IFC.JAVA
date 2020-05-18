package buildingsmart.ifc;

import buildingsmart.util.Functions;
import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * The extruded area solid (<I>IfcExtrudedAreaSolid</I>) is defined by sweeping
 * a bounded planar surface. The direction of the extrusion is given by the
 * <I>ExtrudedDirection</I>
 * attribute and the length of the extrusion is given by the
 * <I>Depth</I>
 * attribute. If the planar area has inner boundaries, i.e. holes defined, then
 * those holes shall be swept into holes of the solid.</P>
 * <BLOCKQUOTE>
 * <P><FONT SIZE="-1"><U>New</U>: The <I>IfcExtrudedArea</I>
 * solid now directly defines the linear extrusion of a cross section (also
 * referred to as profile). It thereby combines the functionality of the
 * previous IfcAttDrivenExtrudedSegment entity. In contrary to the previous
 * IfcAttDrivenExtrudedSegment, the extruded direction can be any which is not
 * perpendicular to the z axis of the position coordinate
 * system.</FONT></P></BLOCKQUOTE>
 * <P>The <I>ExtrudedDirection</I> is given within the position
 * coordinate system as defined by <I>IfcSweptAreaSolid.Position</I>. Extrusions
 * are not longer restricted to be perpendicular to the extruded surface of the
 * profile.</P>
 */
public class IfcExtrudedAreaSolid extends IfcSweptAreaSolid {
    private final IfcDirection extrudedDirection;
    private final IfcLengthMeasure depth;

    /**
     * @param sweptArea         The surface defining the area to be swept. It is
     *                          given as a profile definition within the xy
     *                          plane of the position coordinate system. Its
     *                          profileType must be AREA.
     * @param position          Position coordinate system for the swept area.
     * @param extrudedDirection The direction in which the surface is to be
     *                          swept. It shall not be perpendicular to the
     *                          local z-axis.
     * @param depth             The distance the surface is to be swept.
     * @throws IllegalArgumentException If sweptArea, position,
     * extrudedDirection
     *                                  or depth are null; if sweptArea
     *                                  .profileType is not AREA; if
     *                                  extrudedDirection is perpendicular to
     *                                  the local z-axis.
     */
    public IfcExtrudedAreaSolid(@NotNull IfcProfileDef sweptArea,
                                @NotNull IfcAxis2Placement3D position,
                                @NotNull IfcDirection extrudedDirection,
                                @NotNull IfcLengthMeasure depth) {
        super(sweptArea, position);
        if (extrudedDirection == null) {
            throw new IllegalArgumentException(
                    "extrudedDirection cannot be null");
        }
        if (depth == null) {
            throw new IllegalArgumentException("depth cannot be null");
        }
        if (Functions
                .ifcDotProduct(new IfcDirection(0, 0, 1), extrudedDirection)
                .getValue() == 0) {
            throw new IllegalArgumentException(
                    "extrudedDirection cannot be perpendicular to the local " +
                            "z-axis");
        }
        this.extrudedDirection = extrudedDirection;
        this.depth = depth;
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
        IfcExtrudedAreaSolid that = (IfcExtrudedAreaSolid) o;
        return extrudedDirection.equals(that.extrudedDirection) &&
                depth.equals(that.depth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), extrudedDirection, depth);
    }
}
