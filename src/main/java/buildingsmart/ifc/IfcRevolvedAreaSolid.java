package buildingsmart.ifc;

public abstract class IfcRevolvedAreaSolid extends IfcSweptAreaSolid {
    private final IfcAxis1Placement Axis;
    private double Angle;
    private IfcLine AxisLine;

    public IfcRevolvedAreaSolid(IfcProfileDef sweptArea,
                                IfcAxis2Placement3D position,
                                IfcAxis1Placement axis) {

        super(sweptArea, position);
        Axis = axis;
    }
}
