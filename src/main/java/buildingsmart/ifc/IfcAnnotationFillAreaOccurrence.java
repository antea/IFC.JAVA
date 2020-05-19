package buildingsmart.ifc;

import java.util.Set;

public abstract class IfcAnnotationFillAreaOccurrence
        extends IfcAnnotationOccurrence {
    private final IfcPoint FillStyleTarget;
    private IfcGlobalOrLocalEnum GlobalOrLocal;

    public IfcAnnotationFillAreaOccurrence(IfcRepresentationItem item,
                                           Set<IfcPresentationStyleAssignment> styles,
                                           IfcLabel name,
                                           IfcPoint fillStyleTarget) {

        super(item, styles, name);
        FillStyleTarget = fillStyleTarget;
    }
}
