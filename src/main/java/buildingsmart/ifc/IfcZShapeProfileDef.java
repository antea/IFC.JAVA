package buildingsmart.ifc;

public class IfcZShapeProfileDef extends IfcParameterizedProfileDef {
    private final IfcLengthMeasure Depth;
    private IfcLengthMeasure FlangeWidth;
    private IfcLengthMeasure WebThickness;
    private IfcLengthMeasure FlangeThickness;
    private IfcLengthMeasure FilletRadius;
    private IfcLengthMeasure EdgeRadius;

    public IfcZShapeProfileDef(IfcProfileTypeEnum profileType,
                               IfcLabel profileName,
                               IfcAxis2Placement2D position,
                               IfcLengthMeasure depth) {
        super(profileType, profileName, position);
        Depth = depth;
    }
}
