package buildingsmart.ifc;

public class IfcCShapeProfileDef extends IfcParameterizedProfileDef {
    private final IfcLengthMeasure Depth;
    private IfcLengthMeasure Width;
    private IfcLengthMeasure WallThickness;
    private IfcLengthMeasure Girth;
    private IfcLengthMeasure InternalFilletRadius;
    private IfcLengthMeasure CentreOfGravityInX;

    public IfcCShapeProfileDef(IfcProfileTypeEnum profileType,
                               IfcLabel profileName,
                               IfcAxis2Placement2D position,
                               IfcLengthMeasure depth) {
        super(profileType, profileName, position);
        Depth = depth;
    }
}
