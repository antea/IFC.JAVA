package buildingsmart.ifc;

public class IfcTransformerType extends IfcEnergyConversionDeviceType {
    private final IfcTransformerTypeEnum PredefinedType;

    public IfcTransformerType(IfcGloballyUniqueId globalId,
                              IfcOwnerHistory ownerHistory, IfcLabel name,
                              IfcText description, String applicableOccurrence,
                              IfcPropertySetDefinition[] hasPropertySets,
                              IfcRelDefinesByType[] objectTypeOf,
                              IfcRepresentationMap[] representationMaps,
                              String tag, String elementType,
                              IfcTransformerTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
