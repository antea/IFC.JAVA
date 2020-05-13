package buildingsmart.ifc;

public class IfcDerivedUnit implements IfcUnit {
    private IfcDerivedUnitElement[] elements;
    private IfcDerivedUnitEnum unitType;
    private String userDefinedType;
    private IfcDimensionalExponents dimensions;

    public IfcDerivedUnitEnum getUnitType() {
        return unitType;
    }
}
