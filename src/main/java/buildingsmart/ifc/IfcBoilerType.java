package buildingsmart.ifc;

public class IfcBoilerType extends IfcEnergyConversionDeviceType {
    private final IfcBoilerTypeEnum PredefinedType;

    public IfcBoilerType(IfcGloballyUniqueId globalId,
                         IfcOwnerHistory ownerHistory, IfcLabel name,
                         IfcText description, String applicableOccurrence,
                         IfcPropertySetDefinition[] hasPropertySets,
                         IfcRelDefinesByType[] objectTypeOf,
                         IfcRepresentationMap[] representationMaps, String tag,
                         String elementType, IfcBoilerTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
