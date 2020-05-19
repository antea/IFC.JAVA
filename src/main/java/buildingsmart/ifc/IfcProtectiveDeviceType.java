package buildingsmart.ifc;

public class IfcProtectiveDeviceType extends IfcFlowControllerType {
    private final IfcProtectiveDeviceTypeEnum PredefinedType;

    public IfcProtectiveDeviceType(IfcGloballyUniqueId globalId,
                                   IfcOwnerHistory ownerHistory, IfcLabel name,
                                   IfcText description,
                                   String applicableOccurrence,
                                   IfcPropertySetDefinition[] hasPropertySets,
                                   IfcRelDefinesByType[] objectTypeOf,
                                   IfcRepresentationMap[] representationMaps,
                                   String tag, String elementType,
                                   IfcProtectiveDeviceTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
