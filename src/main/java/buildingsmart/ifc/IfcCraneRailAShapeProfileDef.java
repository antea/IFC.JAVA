package buildingsmart.ifc;

public class IfcCraneRailAShapeProfileDef extends IfcParameterizedProfileDef {
    private final IfcLengthMeasure OverallHeight;
    private IfcLengthMeasure BaseWidth2;
    private IfcLengthMeasure Radius;
    private IfcLengthMeasure HeadWidth;
    private IfcLengthMeasure HeadDepth2;
    private IfcLengthMeasure HeadDepth3;
    private IfcLengthMeasure WebThickness;
    private IfcLengthMeasure BaseWidth4;
    private IfcLengthMeasure BaseDepth1;
    private IfcLengthMeasure BaseDepth2;
    private IfcLengthMeasure BaseDepth3;
    private IfcLengthMeasure CentreOfGravityInY;

    public IfcCraneRailAShapeProfileDef(IfcProfileTypeEnum profileType,
                                        IfcLabel profileName,
                                        IfcAxis2Placement2D position,
                                        IfcLengthMeasure overallHeight) {

        super(profileType, profileName, position);
        OverallHeight = overallHeight;
    }
}
