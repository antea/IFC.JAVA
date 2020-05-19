package buildingsmart.ifc;

public class IfcCenterLineProfileDef extends IfcArbitraryOpenProfileDef {
    private final IfcLengthMeasure Thickness;

    public IfcCenterLineProfileDef(IfcProfileTypeEnum profileType,
                                   IfcLabel profileName, IfcBoundedCurve curve,
                                   IfcLengthMeasure thickness) {

        super(profileType, profileName, curve);
        Thickness = thickness;
    }
}
