package buildingsmart.ifc;

public class IfcCoolingTowerType extends IfcEnergyConversionDeviceType {
    private final IfcCoolingTowerTypeEnum PredefinedType;

    public IfcCoolingTowerType(IfcGloballyUniqueId globalId,
                               IfcOwnerHistory ownerHistory, IfcLabel name,
                               IfcText description, String applicableOccurrence,
                               IfcPropertySetDefinition[] hasPropertySets,
                               IfcRelDefinesByType[] objectTypeOf,
                               IfcRepresentationMap[] representationMaps,
                               String tag, String elementType,
                               IfcCoolingTowerTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
