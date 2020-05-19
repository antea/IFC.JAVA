package buildingsmart.ifc;

import java.util.Set;

public abstract class IfcAnnotationSurfaceOccurrence
        extends IfcAnnotationOccurrence {
    public IfcAnnotationSurfaceOccurrence(IfcRepresentationItem item,
                                          Set<IfcPresentationStyleAssignment> styles,
                                          IfcLabel name) {
        super(item, styles, name);
    }
}
