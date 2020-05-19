package buildingsmart.ifc;

public class IfcUShapeProfileDef extends IfcParameterizedProfileDef {
    private final IfcLengthMeasure Depth;
    private IfcLengthMeasure FlangeWidth;
    private IfcLengthMeasure WebThickness;
    private IfcLengthMeasure FlangeThickness;
    private IfcLengthMeasure FilletRadius;
    private IfcLengthMeasure EdgeRadius;
    private double FlangeSlope;
    private IfcLengthMeasure CentreOfGravityInX;

    public IfcUShapeProfileDef(IfcProfileTypeEnum profileType,
                               IfcLabel profileName,
                               IfcAxis2Placement2D position,
                               IfcLengthMeasure depth) {
        super(profileType, profileName, position);
        Depth = depth;
    }
}
