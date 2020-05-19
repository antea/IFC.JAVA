package buildingsmart.ifc;

public class IfcArbitraryClosedProfileDef extends IfcProfileDef {
    private final IfcCurve OuterCurve;

    public IfcArbitraryClosedProfileDef(IfcProfileTypeEnum profileType,
                                        IfcLabel profileName,
                                        IfcCurve outerCurve) {
        super(profileType, profileName);
        OuterCurve = outerCurve;
    }
}
