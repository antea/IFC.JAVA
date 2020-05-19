package buildingsmart.ifc;

public class IfcRoundedRectangleProfileDef extends IfcRectangleProfileDef {
    private final IfcLengthMeasure RoundingRadius;

    public IfcRoundedRectangleProfileDef(IfcProfileTypeEnum profileType,
                                         IfcLabel profileName,
                                         IfcAxis2Placement2D position,
                                         IfcLengthMeasure XDim,
                                         IfcLengthMeasure roundingRadius) {
        super(profileType, profileName, position, XDim);
        RoundingRadius = roundingRadius;
    }
}
