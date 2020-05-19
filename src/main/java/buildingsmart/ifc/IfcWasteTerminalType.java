package buildingsmart.ifc;

public class IfcWasteTerminalType extends IfcFlowTerminalType {
    private final IfcWasteTerminalTypeEnum PredefinedType;

    public IfcWasteTerminalType(IfcGloballyUniqueId globalId,
                                IfcOwnerHistory ownerHistory, IfcLabel name,
                                IfcText description,
                                String applicableOccurrence,
                                IfcPropertySetDefinition[] hasPropertySets,
                                IfcRelDefinesByType[] objectTypeOf,
                                IfcRepresentationMap[] representationMaps,
                                String tag, String elementType,
                                IfcWasteTerminalTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
