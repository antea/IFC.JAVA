package buildingsmart.ifc;

import com.sun.istack.internal.NotNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * The <i>IfcStyledItem</i> holds presentation style information for products,
 * either explicitly for an
 * <i>IfcGeometricRepresentationItem</i>
 * being part of an <i>IfcShapeRepresentation</i> assigned to a product, or by
 * assigning presentation information to <i>IfcMaterial</i> being assigned as
 * other representation for a product.</p>
 */
public class IfcStyledItem extends IfcRepresentationItem {
    private final IfcRepresentationItem item;
    private final Set<IfcPresentationStyleAssignment> styles;
    private final IfcLabel name;

    /**
     * @param item   A geometric representation item to which the style is
     *               assigned.
     * @param styles Representation style assignments which are assigned to an
     *               item. NOTE: In current IFC release only one presentation
     *               style assignment shall be assigned.
     * @param name   The word, or group of words, by which the styled item is
     *               referred to.
     * @throws IllegalArgumentException If styles is null or if its size is not
     *                                  1; if item is of type IfcStyledItem.
     */
    public IfcStyledItem(IfcRepresentationItem item,
                         @NotNull Set<IfcPresentationStyleAssignment> styles,
                         IfcLabel name) {
        if (styles == null) {
            throw new IllegalArgumentException("styles cannot be null");
        }
        if (styles.size() != 1) {
            throw new IllegalArgumentException("size of styles must be 1");
        }
        if (item instanceof IfcStyledItem) {
            throw new IllegalArgumentException(
                    "item cannot be of type IfcStyledItem");
        }
        this.item = item;
        this.styles = styles;
        this.name = name;
    }

    /**
     * @param item   A geometric representation item to which the style is
     *               assigned.
     * @param styles Representation style assignments which are assigned to an
     *               item. NOTE: In current IFC release only one presentation
     *               style assignment shall be assigned.
     * @param name   The word, or group of words, by which the styled item is
     *               referred to.
     * @throws IllegalArgumentException If styles is null; if item is of type
     *                                  IfcStyledItem.
     */
    public IfcStyledItem(IfcRepresentationItem item,
                         @NotNull IfcPresentationStyleAssignment styles,
                         IfcLabel name) {
        if (styles == null) {
            throw new IllegalArgumentException("styles cannot be null");
        }
        if (item instanceof IfcStyledItem) {
            throw new IllegalArgumentException(
                    "item cannot be of type IfcStyledItem");
        }
        this.item = item;
        /*
        there's an ArrayList under the hood, in this case it's a better
        choice than HashSet because there's no point in calculating an
        hash if there's only one element in the Set
         */
        this.styles =
                new CopyOnWriteArraySet<>(Collections.singletonList(styles));
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
        if (!super.equals(o)) {
            return false;
        }
        IfcStyledItem that = (IfcStyledItem) o;
        return Objects.equals(item, that.item) && styles.equals(that.styles) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), item, styles, name);
    }
}
