package buildingsmart.ifc;

public class IfcEnergyProperties extends IfcPropertySetDefinition {
    private final IfcEnergySequenceEnum EnergySequence;
    private String UserDefinedEnergySequence;

    public IfcEnergyProperties(IfcGloballyUniqueId globalId,
                               IfcOwnerHistory ownerHistory, IfcLabel name,
                               IfcText description,
                               IfcRelAssociates[] hasAssociations,
                               IfcRelDefinesByProperties[] propertyDefinitionOf,
                               IfcEnergySequenceEnum energySequence) {

        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf);
        EnergySequence = energySequence;
    }
}
