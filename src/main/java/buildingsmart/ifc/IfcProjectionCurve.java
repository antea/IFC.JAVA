package buildingsmart.ifc;

import java.util.Set;

public abstract class IfcProjectionCurve extends IfcAnnotationCurveOccurrence {
    public IfcProjectionCurve(IfcRepresentationItem item,
                              Set<IfcPresentationStyleAssignment> styles,
                              IfcLabel name) {
        super(item, styles, name);
    }
}
