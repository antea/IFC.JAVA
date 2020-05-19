package buildingsmart.ifc;

public class IfcStackTerminalType extends IfcFlowTerminalType {
    private final IfcStackTerminalTypeEnum PredefinedType;

    public IfcStackTerminalType(IfcGloballyUniqueId globalId,
                                IfcOwnerHistory ownerHistory, IfcLabel name,
                                IfcText description,
                                String applicableOccurrence,
                                IfcPropertySetDefinition[] hasPropertySets,
                                IfcRelDefinesByType[] objectTypeOf,
                                IfcRepresentationMap[] representationMaps,
                                String tag, String elementType,
                                IfcStackTerminalTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
