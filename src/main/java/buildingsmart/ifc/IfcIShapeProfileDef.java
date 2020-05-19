package buildingsmart.ifc;

public class IfcIShapeProfileDef extends IfcParameterizedProfileDef {
    private final IfcLengthMeasure OverallWidth;
    private IfcLengthMeasure OverallDepth;
    private IfcLengthMeasure WebThickness;
    private IfcLengthMeasure FlangeThickness;
    private IfcLengthMeasure FilletRadius;

    public IfcIShapeProfileDef(IfcProfileTypeEnum profileType,
                               IfcLabel profileName,
                               IfcAxis2Placement2D position,
                               IfcLengthMeasure overallWidth) {
        super(profileType, profileName, position);
        OverallWidth = overallWidth;
    }
}
