package buildingsmart.ifc;

public class IfcPropertySet extends IfcPropertySetDefinition {
    private final IfcProperty[] HasProperties;

    public IfcPropertySet(IfcGloballyUniqueId globalId,
                          IfcOwnerHistory ownerHistory, IfcLabel name,
                          IfcText description,
                          IfcRelAssociates[] hasAssociations,
                          IfcRelDefinesByProperties[] propertyDefinitionOf,
                          IfcProperty[] hasProperties) {

        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf);
        HasProperties = hasProperties;
    }
}
