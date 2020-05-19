package buildingsmart.ifc;

public class IfcCircleHollowProfileDef extends IfcCircleProfileDef {
    private final IfcLengthMeasure WallThickness;

    public IfcCircleHollowProfileDef(IfcProfileTypeEnum profileType,
                                     IfcLabel profileName,
                                     IfcAxis2Placement2D position,
                                     IfcPositiveLengthMeasure radius,
                                     IfcLengthMeasure wallThickness) {
        super(profileType, profileName, position, radius);
        WallThickness = wallThickness;
    }
}
