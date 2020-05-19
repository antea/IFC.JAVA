package buildingsmart.ifc;

public class IfcCraneRailFShapeProfileDef extends IfcParameterizedProfileDef {
    private final IfcLengthMeasure OverallHeight;
    private IfcLengthMeasure HeadWidth;
    private IfcLengthMeasure Radius;
    private IfcLengthMeasure HeadDepth2;
    private IfcLengthMeasure HeadDepth3;
    private IfcLengthMeasure WebThickness;
    private IfcLengthMeasure BaseDepth1;
    private IfcLengthMeasure BaseDepth2;
    private IfcLengthMeasure CentreOfGravityInY;

    public IfcCraneRailFShapeProfileDef(IfcProfileTypeEnum profileType,
                                        IfcLabel profileName,
                                        IfcAxis2Placement2D position,
                                        IfcLengthMeasure overallHeight) {
        super(profileType, profileName, position);
        OverallHeight = overallHeight;
    }
}
