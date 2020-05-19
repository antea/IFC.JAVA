package buildingsmart.ifc;

public class IfcDerivedProfileDef extends IfcProfileDef {
    private final IfcProfileDef ParentProfile;
    private IfcCartesianTransformationOperator2D Operator;
    private String Label;

    public IfcDerivedProfileDef(IfcProfileTypeEnum profileType,
                                IfcLabel profileName,
                                IfcProfileDef parentProfile) {
        super(profileType, profileName);
        ParentProfile = parentProfile;
    }
}
