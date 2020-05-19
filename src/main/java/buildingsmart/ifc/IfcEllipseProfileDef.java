package buildingsmart.ifc;

public class IfcEllipseProfileDef extends IfcParameterizedProfileDef {
    private final IfcLengthMeasure SemiAxis1;
    private IfcLengthMeasure SemiAxis2;

    public IfcEllipseProfileDef(IfcProfileTypeEnum profileType,
                                IfcLabel profileName,
                                IfcAxis2Placement2D position,
                                IfcLengthMeasure semiAxis1) {
        super(profileType, profileName, position);
        SemiAxis1 = semiAxis1;
    }
}
