package buildingsmart.ifc;

import java.util.Set;

public abstract class IfcAnnotationOccurrence extends IfcStyledItem {
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
    public IfcAnnotationOccurrence(IfcRepresentationItem item,
                                   Set<IfcPresentationStyleAssignment> styles,
                                   IfcLabel name) {

        super(item, styles, name);
    }
}
