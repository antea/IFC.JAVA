package buildingsmart.ifc;

import java.util.Set;

public abstract class IfcTerminatorSymbol
        extends IfcAnnotationSymbolOccurrence {
    private final IfcAnnotationCurveOccurrence AnnotatedCurve;

    public IfcTerminatorSymbol(IfcRepresentationItem item,
                               Set<IfcPresentationStyleAssignment> styles,
                               IfcLabel name,
                               IfcAnnotationCurveOccurrence annotatedCurve) {
        super(item, styles, name);
        AnnotatedCurve = annotatedCurve;
    }
}
