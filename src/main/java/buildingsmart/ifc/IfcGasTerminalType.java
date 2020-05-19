package buildingsmart.ifc;

public class IfcGasTerminalType extends IfcFlowTerminalType {
    private final IfcGasTerminalTypeEnum PredefinedType;

    public IfcGasTerminalType(IfcGloballyUniqueId globalId,
                              IfcOwnerHistory ownerHistory, IfcLabel name,
                              IfcText description, String applicableOccurrence,
                              IfcPropertySetDefinition[] hasPropertySets,
                              IfcRelDefinesByType[] objectTypeOf,
                              IfcRepresentationMap[] representationMaps,
                              String tag, String elementType,
                              IfcGasTerminalTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
