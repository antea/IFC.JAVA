package buildingsmart.ifc;

public class IfcControllerType extends IfcDistributionControlElementType {
    private final IfcControllerTypeEnum PredefinedType;

    public IfcControllerType(IfcGloballyUniqueId globalId,
                             IfcOwnerHistory ownerHistory, IfcLabel name,
                             IfcText description, String applicableOccurrence,
                             IfcPropertySetDefinition[] hasPropertySets,
                             IfcRelDefinesByType[] objectTypeOf,
                             IfcRepresentationMap[] representationMaps,
                             String tag, String elementType,
                             IfcControllerTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
