package buildingsmart.ifc;

public class IfcReinforcingBar extends IfcReinforcingElement {
    private final IfcLengthMeasure NominalDiameter;
    private double CrossSectionArea;
    private IfcLengthMeasure BarLength;
    private IfcReinforcingBarRoleEnum BarRole;
    private IfcReinforcingBarSurfaceEnum BarSurface;

    public IfcReinforcingBar(IfcGloballyUniqueId globalId,
                             IfcOwnerHistory ownerHistory, IfcLabel name,
                             IfcText description, IfcLabel objectType,
                             String steelGrade,
                             IfcLengthMeasure nominalDiameter) {
        super(globalId, ownerHistory, name, description, objectType,
                steelGrade);
        NominalDiameter = nominalDiameter;
    }
}
