package buildingsmart.ifc;

import java.util.Set;

public abstract class IfcAnnotationTextOccurrence
        extends IfcAnnotationOccurrence {
    public IfcAnnotationTextOccurrence(IfcRepresentationItem item,
                                       Set<IfcPresentationStyleAssignment> styles,
                                       IfcLabel name) {
        super(item, styles, name);
    }
}
