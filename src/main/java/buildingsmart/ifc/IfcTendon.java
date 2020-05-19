package buildingsmart.ifc;

public class IfcTendon extends IfcReinforcingElement {
    private final IfcTendonTypeEnum PredefinedType;
    private IfcLengthMeasure NominalDiameter;
    private double CrossSectionArea;
    private double TensionForce;
    private double PreStress;
    private IfcRatioMeasure FrictionCoefficient;
    private IfcLengthMeasure AnchorageSlip;
    private IfcLengthMeasure MinCurvatureRadius;

    public IfcTendon(IfcGloballyUniqueId globalId, IfcOwnerHistory ownerHistory,
                     IfcLabel name, IfcText description, IfcLabel objectType,
                     String steelGrade, IfcTendonTypeEnum predefinedType) {

        super(globalId, ownerHistory, name, description, objectType,
                steelGrade);
        PredefinedType = predefinedType;
    }
}
