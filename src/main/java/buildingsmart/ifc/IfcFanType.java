package buildingsmart.ifc;

public class IfcFanType extends IfcFlowMovingDeviceType {
    private final IfcFanTypeEnum PredefinedType;

    public IfcFanType(IfcGloballyUniqueId globalId,
                      IfcOwnerHistory ownerHistory, IfcLabel name,
                      IfcText description, String applicableOccurrence,
                      IfcPropertySetDefinition[] hasPropertySets,
                      IfcRelDefinesByType[] objectTypeOf,
                      IfcRepresentationMap[] representationMaps, String tag,
                      String elementType, IfcFanTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
