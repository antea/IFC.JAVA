package buildingsmart.ifc;

public class IfcRectangleHollowProfileDef extends IfcRectangleProfileDef {
    private final IfcLengthMeasure WallThickness;
    private IfcLengthMeasure InnerFilletRadius;
    private IfcLengthMeasure OuterFilletRadius;

    public IfcRectangleHollowProfileDef(IfcProfileTypeEnum profileType,
                                        IfcLabel profileName,
                                        IfcAxis2Placement2D position,
                                        IfcLengthMeasure XDim,
                                        IfcLengthMeasure wallThickness) {

        super(profileType, profileName, position, XDim);
        WallThickness = wallThickness;
    }
}
