package buildingsmart.ifc;

import java.util.Set;

public abstract class IfcAnnotationSymbolOccurrence
        extends IfcAnnotationOccurrence {
    public IfcAnnotationSymbolOccurrence(IfcRepresentationItem item,
                                         Set<IfcPresentationStyleAssignment> styles,
                                         IfcLabel name) {
        super(item, styles, name);
    }
}
