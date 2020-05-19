package buildingsmart.ifc;

public class IfcPumpType extends IfcFlowMovingDeviceType {
    private final IfcPumpTypeEnum PredefinedType;

    public IfcPumpType(IfcGloballyUniqueId globalId,
                       IfcOwnerHistory ownerHistory, IfcLabel name,
                       IfcText description, String applicableOccurrence,
                       IfcPropertySetDefinition[] hasPropertySets,
                       IfcRelDefinesByType[] objectTypeOf,
                       IfcRepresentationMap[] representationMaps, String tag,
                       String elementType, IfcPumpTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
