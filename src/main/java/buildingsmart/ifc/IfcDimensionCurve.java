package buildingsmart.ifc;

import java.util.Set;

public abstract class IfcDimensionCurve extends IfcAnnotationCurveOccurrence {
    private final IfcTerminatorSymbol[] AnnotatedBySymbols;

    public IfcDimensionCurve(IfcRepresentationItem item,
                             Set<IfcPresentationStyleAssignment> styles,
                             IfcLabel name,
                             IfcTerminatorSymbol[] annotatedBySymbols) {
        super(item, styles, name);
        AnnotatedBySymbols = annotatedBySymbols;
    }
}
