package buildingsmart.ifc;

public class IfcValveType extends IfcFlowControllerType {
    private final IfcValveTypeEnum PredefinedType;

    public IfcValveType(IfcGloballyUniqueId globalId,
                        IfcOwnerHistory ownerHistory, IfcLabel name,
                        IfcText description, String applicableOccurrence,
                        IfcPropertySetDefinition[] hasPropertySets,
                        IfcRelDefinesByType[] objectTypeOf,
                        IfcRepresentationMap[] representationMaps, String tag,
                        String elementType, IfcValveTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
