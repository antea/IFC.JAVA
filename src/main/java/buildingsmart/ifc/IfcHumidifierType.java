package buildingsmart.ifc;

public class IfcHumidifierType extends IfcEnergyConversionDeviceType {
    private final IfcHumidifierTypeEnum PredefinedType;

    public IfcHumidifierType(IfcGloballyUniqueId globalId,
                             IfcOwnerHistory ownerHistory, IfcLabel name,
                             IfcText description, String applicableOccurrence,
                             IfcPropertySetDefinition[] hasPropertySets,
                             IfcRelDefinesByType[] objectTypeOf,
                             IfcRepresentationMap[] representationMaps,
                             String tag, String elementType,
                             IfcHumidifierTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
