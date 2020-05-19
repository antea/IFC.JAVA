package buildingsmart.ifc;

public class IfcAsymmetricIShapeProfileDef extends IfcIShapeProfileDef {
    private final IfcLengthMeasure TopFlangeWidth;
    private IfcLengthMeasure TopFlangeThickness;
    private IfcLengthMeasure TopFlangeFilletRadius;
    private IfcLengthMeasure CentreOfGravityInY;

    public IfcAsymmetricIShapeProfileDef(IfcProfileTypeEnum profileType,
                                         IfcLabel profileName,
                                         IfcAxis2Placement2D position,
                                         IfcLengthMeasure overallWidth,
                                         IfcLengthMeasure topFlangeWidth) {
        super(profileType, profileName, position, overallWidth);
        TopFlangeWidth = topFlangeWidth;
    }
}
