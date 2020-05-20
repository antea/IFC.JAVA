package buildingsmart.ifc;

public abstract class IfcCurve extends IfcGeometricRepresentationItem {
    private IfcDimensionCount dim;

    public IfcDimensionCount getDim() {
        return dim;
    }
}
