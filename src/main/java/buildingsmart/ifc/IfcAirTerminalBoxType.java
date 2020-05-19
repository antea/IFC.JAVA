package buildingsmart.ifc;

public class IfcAirTerminalBoxType extends IfcFlowControllerType {
    private final IfcAirTerminalBoxTypeEnum PredefinedType;

    public IfcAirTerminalBoxType(IfcGloballyUniqueId globalId,
                                 IfcOwnerHistory ownerHistory, IfcLabel name,
                                 IfcText description,
                                 String applicableOccurrence,
                                 IfcPropertySetDefinition[] hasPropertySets,
                                 IfcRelDefinesByType[] objectTypeOf,
                                 IfcRepresentationMap[] representationMaps,
                                 String tag, String elementType,
                                 IfcAirTerminalBoxTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
