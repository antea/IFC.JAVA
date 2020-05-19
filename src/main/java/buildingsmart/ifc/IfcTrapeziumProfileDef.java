package buildingsmart.ifc;

public class IfcTrapeziumProfileDef extends IfcParameterizedProfileDef {
    private final IfcLengthMeasure BottomXDim;
    private IfcLengthMeasure TopXDim;
    private IfcLengthMeasure YDim;
    private double TopXOffset;

    public IfcTrapeziumProfileDef(IfcProfileTypeEnum profileType,
                                  IfcLabel profileName,
                                  IfcAxis2Placement2D position,
                                  IfcLengthMeasure bottomXDim) {
        super(profileType, profileName, position);
        BottomXDim = bottomXDim;
    }
}
