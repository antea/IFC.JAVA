package buildingsmart.ifc;

public class IfcUnitaryEquipmentType extends IfcEnergyConversionDeviceType {
    private final IfcUnitaryEquipmentTypeEnum PredefinedType;

    public IfcUnitaryEquipmentType(IfcGloballyUniqueId globalId,
                                   IfcOwnerHistory ownerHistory, IfcLabel name,
                                   IfcText description,
                                   String applicableOccurrence,
                                   IfcPropertySetDefinition[] hasPropertySets,
                                   IfcRelDefinesByType[] objectTypeOf,
                                   IfcRepresentationMap[] representationMaps,
                                   String tag, String elementType,
                                   IfcUnitaryEquipmentTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
