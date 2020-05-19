package buildingsmart.ifc;

public class IfcAirTerminalType extends IfcFlowTerminalType {
    private final IfcAirTerminalTypeEnum PredefinedType;

    public IfcAirTerminalType(IfcGloballyUniqueId globalId,
                              IfcOwnerHistory ownerHistory, IfcLabel name,
                              IfcText description, String applicableOccurrence,
                              IfcPropertySetDefinition[] hasPropertySets,
                              IfcRelDefinesByType[] objectTypeOf,
                              IfcRepresentationMap[] representationMaps,
                              String tag, String elementType,
                              IfcAirTerminalTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
