package buildingsmart.ifc;

public class IfcPropertySetDefinition extends IfcPropertyDefinition {
    private final IfcRelDefinesByProperties[] PropertyDefinitionOf;
    private IfcTypeObject[] DefinesType;

    public IfcPropertySetDefinition(IfcGloballyUniqueId globalId,
                                    IfcOwnerHistory ownerHistory, IfcLabel name,
                                    IfcText description,
                                    IfcRelAssociates[] hasAssociations,
                                    IfcRelDefinesByProperties[] propertyDefinitionOf) {
        super(globalId, ownerHistory, name, description, hasAssociations);
        PropertyDefinitionOf = propertyDefinitionOf;
    }
}
