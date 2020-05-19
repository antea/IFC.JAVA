package buildingsmart.ifc;

public class IfcElectricFlowStorageDeviceType extends IfcFlowStorageDeviceType {
    private final IfcElectricFlowStorageDeviceTypeEnum PredefinedType;

    public IfcElectricFlowStorageDeviceType(IfcGloballyUniqueId globalId,
                                            IfcOwnerHistory ownerHistory,
                                            IfcLabel name, IfcText description,
                                            String applicableOccurrence,
                                            IfcPropertySetDefinition[] hasPropertySets,
                                            IfcRelDefinesByType[] objectTypeOf,
                                            IfcRepresentationMap[] representationMaps,
                                            String tag, String elementType,
                                            IfcElectricFlowStorageDeviceTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
