package buildingsmart.ifc;

public class IfcTShapeProfileDef extends IfcParameterizedProfileDef {
    private final IfcLengthMeasure Depth;
    private IfcLengthMeasure FlangeWidth;
    private IfcLengthMeasure WebThickness;
    private IfcLengthMeasure FlangeThickness;
    private IfcLengthMeasure FilletRadius;
    private IfcLengthMeasure FlangeEdgeRadius;
    private IfcLengthMeasure WebEdgeRadius;
    private double WebSlope;
    private double FlangeSlope;
    private IfcLengthMeasure CentreOfGravityInY;

    public IfcTShapeProfileDef(IfcProfileTypeEnum profileType,
                               IfcLabel profileName,
                               IfcAxis2Placement2D position,
                               IfcLengthMeasure depth) {
        super(profileType, profileName, position);
        Depth = depth;
    }
}
