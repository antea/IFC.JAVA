package buildingsmart.ifc;

public class IfcCondenserType extends IfcEnergyConversionDeviceType {
    private final IfcCondenserTypeEnum PredefinedType;

    public IfcCondenserType(IfcGloballyUniqueId globalId,
                            IfcOwnerHistory ownerHistory, IfcLabel name,
                            IfcText description, String applicableOccurrence,
                            IfcPropertySetDefinition[] hasPropertySets,
                            IfcRelDefinesByType[] objectTypeOf,
                            IfcRepresentationMap[] representationMaps,
                            String tag, String elementType,
                            IfcCondenserTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
