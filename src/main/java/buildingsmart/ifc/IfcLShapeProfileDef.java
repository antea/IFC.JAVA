package buildingsmart.ifc;

public class IfcLShapeProfileDef extends IfcParameterizedProfileDef {
    private final IfcLengthMeasure Depth;
    private IfcLengthMeasure Width;
    private IfcLengthMeasure Thickness;
    private IfcLengthMeasure FilletRadius;
    private IfcLengthMeasure EdgeRadius;
    private double LegSlope;
    private IfcLengthMeasure CentreOfGravityInX;
    private IfcLengthMeasure CentreOfGravityInY;

    public IfcLShapeProfileDef(IfcProfileTypeEnum profileType,
                               IfcLabel profileName,
                               IfcAxis2Placement2D position,
                               IfcLengthMeasure depth) {
        super(profileType, profileName, position);
        Depth = depth;
    }
}
