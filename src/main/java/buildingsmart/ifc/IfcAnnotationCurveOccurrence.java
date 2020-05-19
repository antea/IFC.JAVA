package buildingsmart.ifc;

import java.util.Set;

public abstract class IfcAnnotationCurveOccurrence
        extends IfcAnnotationOccurrence {
    public IfcAnnotationCurveOccurrence(IfcRepresentationItem item,
                                        Set<IfcPresentationStyleAssignment> styles,
                                        IfcLabel name) {
        super(item, styles, name);
    }
}
