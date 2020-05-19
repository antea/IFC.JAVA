package buildingsmart.ifc;

import java.util.Objects;

/**
 * A representation item is an element of product data that participates in one
 * or more representations or contributes to the definition of another
 * representation item. A representation item contributes to the definition of
 * another representation item when it is referenced by that representation
 * item.
 */
public abstract class IfcRepresentationItem extends IfcEntity {
    //private IfcPresentationLayerAssignment[] LayerAssignments;
    private IfcStyledItem styledByItem; //inverse attribute

    /**
     * @param styledByItem Reference to the IfcStyledItem that provides
     *                     presentation information to the representation, e.g.
     *                     a curve style, including colour and thickness to a
     *                     geometric curve.
     */
    public void setStyledByItem(IfcStyledItem styledByItem) {
        this.styledByItem = styledByItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcRepresentationItem that = (IfcRepresentationItem) o;
        return Objects.equals(styledByItem, that.styledByItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(styledByItem);
    }
}
