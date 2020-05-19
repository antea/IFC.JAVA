package buildingsmart.ifc;

public class IfcElectricalBaseProperties extends IfcEnergyProperties {
    private final IfcElectricCurrentEnum ElectricCurrentType;
    private double InputVoltage;
    private double InputFrequency;
    private double FullLoadCurrent;
    private double MinimumCircuitCurrent;
    private double MaximumPowerInput;
    private double RatedPowerInput;
    private int InputPhase;

    public IfcElectricalBaseProperties(IfcGloballyUniqueId globalId,
                                       IfcOwnerHistory ownerHistory,
                                       IfcLabel name, IfcText description,
                                       IfcRelAssociates[] hasAssociations,
                                       IfcRelDefinesByProperties[] propertyDefinitionOf,
                                       IfcEnergySequenceEnum energySequence,
                                       IfcElectricCurrentEnum electricCurrentType) {
        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf, energySequence);
        ElectricCurrentType = electricCurrentType;
    }
}
