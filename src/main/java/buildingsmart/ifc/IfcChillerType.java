package buildingsmart.ifc;

public class IfcChillerType extends IfcEnergyConversionDeviceType {
    private final IfcChillerTypeEnum PredefinedType;

    public IfcChillerType(IfcGloballyUniqueId globalId,
                          IfcOwnerHistory ownerHistory, IfcLabel name,
                          IfcText description, String applicableOccurrence,
                          IfcPropertySetDefinition[] hasPropertySets,
                          IfcRelDefinesByType[] objectTypeOf,
                          IfcRepresentationMap[] representationMaps, String tag,
                          String elementType,
                          IfcChillerTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
