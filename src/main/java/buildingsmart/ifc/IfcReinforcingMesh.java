package buildingsmart.ifc;

public class IfcReinforcingMesh extends IfcReinforcingElement {
    private final IfcLengthMeasure MeshLength;
    private IfcLengthMeasure MeshWidth;
    private IfcLengthMeasure LongitudinalBarNominalDiameter;
    private IfcLengthMeasure TransverseBarNominalDiameter;
    private double LongitudinalBarCrossSectionArea;
    private double TransverseBarCrossSectionArea;
    private IfcLengthMeasure LongitudinalBarSpacing;
    private IfcLengthMeasure TransverseBarSpacing;

    public IfcReinforcingMesh(IfcGloballyUniqueId globalId,
                              IfcOwnerHistory ownerHistory, IfcLabel name,
                              IfcText description, IfcLabel objectType,
                              String steelGrade, IfcLengthMeasure meshLength) {

        super(globalId, ownerHistory, name, description, objectType,
                steelGrade);
        MeshLength = meshLength;
    }
}
