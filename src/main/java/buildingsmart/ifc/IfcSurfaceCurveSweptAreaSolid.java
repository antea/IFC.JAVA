package buildingsmart.ifc;

public abstract class IfcSurfaceCurveSweptAreaSolid extends IfcSweptAreaSolid {
    private final IfcCurve Directrix;
    private double StartParam;
    private double EndParam;
    private IfcSurface ReferenceSurface;

    public IfcSurfaceCurveSweptAreaSolid(IfcProfileDef sweptArea,
                                         IfcAxis2Placement3D position,
                                         IfcCurve directrix) {
        super(sweptArea, position);
        Directrix = directrix;
    }
}
