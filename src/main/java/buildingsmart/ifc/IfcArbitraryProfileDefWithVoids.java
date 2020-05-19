package buildingsmart.ifc;

public class IfcArbitraryProfileDefWithVoids
        extends IfcArbitraryClosedProfileDef {
    private final IfcCurve[] InnerCurves;

    public IfcArbitraryProfileDefWithVoids(IfcProfileTypeEnum profileType,
                                           IfcLabel profileName,
                                           IfcCurve outerCurve,
                                           IfcCurve[] innerCurves) {
        super(profileType, profileName, outerCurve);
        InnerCurves = innerCurves;
    }
}
