package buildingsmart.ifc;

import java.util.Objects;

/**
 * An abstract generalization of style table for presentation information
 * assigned to geometric representation items. It includes styles for curves,
 * areas, surfaces, text and symbols. Style information may include colour,
 * hatching, rendering, text fonts, etc.</p>
 * <p>Each subtype of&nbsp; <i>IfcPresentationStyle</i>
 * can be assigned to <i>IfcGeometricRepresentationItem</i>'s via the
 * <i>IfcPresentationStyleAssignment</i> through an intermediate
 * <i>IfcStyledItem</i> or one of its subtypes.</p>
 */
public abstract class IfcPresentationStyle extends IfcEntity {
    private final IfcLabel name;

    /**
     * @param name Name of the presentation style.
     */
    public IfcPresentationStyle(IfcLabel name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcPresentationStyle that = (IfcPresentationStyle) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
