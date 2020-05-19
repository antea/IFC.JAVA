package buildingsmart.ifc;

public class IfcReinforcementDefinitionProperties
        extends IfcPropertySetDefinition {
    private final String DefinitionType;
    private IfcSectionReinforcementProperties[] ReinforcementSectionDefinitions;

    public IfcReinforcementDefinitionProperties(IfcGloballyUniqueId globalId,
                                                IfcOwnerHistory ownerHistory,
                                                IfcLabel name,
                                                IfcText description,
                                                IfcRelAssociates[] hasAssociations,
                                                IfcRelDefinesByProperties[] propertyDefinitionOf,
                                                String definitionType) {

        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf);
        DefinitionType = definitionType;
    }
}
