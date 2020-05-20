package buildingsmart.ifc;

import java.util.Set;

public abstract class IfcGeometricSet extends IfcGeometricRepresentationItem {
    private Set<IfcGeometricSetSelect> elements;
    private int dim;

    public Set<IfcGeometricSetSelect> getElements() {
        return elements;
    }
}
