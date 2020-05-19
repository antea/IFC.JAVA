package buildingsmart.ifc;

import java.util.Set;

public abstract class IfcDimensionCurveTerminator extends IfcTerminatorSymbol {
    private final IfcDimensionExtentUsage Role;

    public IfcDimensionCurveTerminator(IfcRepresentationItem item,
                                       Set<IfcPresentationStyleAssignment> styles,
                                       IfcLabel name,
                                       IfcAnnotationCurveOccurrence annotatedCurve,
                                       IfcDimensionExtentUsage role) {

        super(item, styles, name, annotatedCurve);
        Role = role;
    }
}
