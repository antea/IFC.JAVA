package buildingsmart.ifc;

public class IfcSwitchingDeviceType extends IfcFlowControllerType {
    private final IfcSwitchingDeviceTypeEnum PredefinedType;

    public IfcSwitchingDeviceType(IfcGloballyUniqueId globalId,
                                  IfcOwnerHistory ownerHistory, IfcLabel name,
                                  IfcText description,
                                  String applicableOccurrence,
                                  IfcPropertySetDefinition[] hasPropertySets,
                                  IfcRelDefinesByType[] objectTypeOf,
                                  IfcRepresentationMap[] representationMaps,
                                  String tag, String elementType,
                                  IfcSwitchingDeviceTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
