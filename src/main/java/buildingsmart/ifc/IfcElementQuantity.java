package buildingsmart.ifc;

public class IfcElementQuantity extends IfcPropertySetDefinition {
    private final String MethodOfMeasurement;
    private IfcPhysicalQuantity[] Quantities;

    public IfcElementQuantity(IfcGloballyUniqueId globalId,
                              IfcOwnerHistory ownerHistory, IfcLabel name,
                              IfcText description,
                              IfcRelAssociates[] hasAssociations,
                              IfcRelDefinesByProperties[] propertyDefinitionOf,
                              String methodOfMeasurement) {
        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf);
        MethodOfMeasurement = methodOfMeasurement;
    }
}
