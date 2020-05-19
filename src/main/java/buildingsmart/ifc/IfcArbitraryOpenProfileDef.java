package buildingsmart.ifc;

public class IfcArbitraryOpenProfileDef extends IfcProfileDef {
    private final IfcBoundedCurve Curve;

    public IfcArbitraryOpenProfileDef(IfcProfileTypeEnum profileType,
                                      IfcLabel profileName,
                                      IfcBoundedCurve curve) {
        super(profileType, profileName);
        Curve = curve;
    }
}
