package buildingsmart.ifc;

public class IfcSanitaryTerminalType extends IfcFlowTerminalType {
    private final IfcSanitaryTerminalTypeEnum PredefinedType;

    public IfcSanitaryTerminalType(IfcGloballyUniqueId globalId,
                                   IfcOwnerHistory ownerHistory, IfcLabel name,
                                   IfcText description,
                                   String applicableOccurrence,
                                   IfcPropertySetDefinition[] hasPropertySets,
                                   IfcRelDefinesByType[] objectTypeOf,
                                   IfcRepresentationMap[] representationMaps,
                                   String tag, String elementType,
                                   IfcSanitaryTerminalTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
