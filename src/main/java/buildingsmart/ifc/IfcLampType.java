package buildingsmart.ifc;

public class IfcLampType extends IfcFlowTerminalType {
    private final IfcLampTypeEnum PredefinedType;

    public IfcLampType(IfcGloballyUniqueId globalId,
                       IfcOwnerHistory ownerHistory, IfcLabel name,
                       IfcText description, String applicableOccurrence,
                       IfcPropertySetDefinition[] hasPropertySets,
                       IfcRelDefinesByType[] objectTypeOf,
                       IfcRepresentationMap[] representationMaps, String tag,
                       String elementType, IfcLampTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
