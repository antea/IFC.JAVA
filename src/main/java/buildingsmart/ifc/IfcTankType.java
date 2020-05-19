package buildingsmart.ifc;

public class IfcTankType extends IfcFlowStorageDeviceType {
    private final IfcTankTypeEnum PredefinedType;

    public IfcTankType(IfcGloballyUniqueId globalId,
                       IfcOwnerHistory ownerHistory, IfcLabel name,
                       IfcText description, String applicableOccurrence,
                       IfcPropertySetDefinition[] hasPropertySets,
                       IfcRelDefinesByType[] objectTypeOf,
                       IfcRepresentationMap[] representationMaps, String tag,
                       String elementType, IfcTankTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
