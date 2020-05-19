package buildingsmart.ifc;

public class IfcRectangleProfileDef extends IfcParameterizedProfileDef {
    private final IfcLengthMeasure XDim;
    private IfcLengthMeasure YDim;

    public IfcRectangleProfileDef(IfcProfileTypeEnum profileType,
                                  IfcLabel profileName,
                                  IfcAxis2Placement2D position,
                                  IfcLengthMeasure XDim) {
        super(profileType, profileName, position);
        this.XDim = XDim;
    }
}
