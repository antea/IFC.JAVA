package buildingsmart.ifc;

public class IfcCoilType extends IfcEnergyConversionDeviceType {
    private final IfcCoilTypeEnum PredefinedType;

    public IfcCoilType(IfcGloballyUniqueId globalId,
                       IfcOwnerHistory ownerHistory, IfcLabel name,
                       IfcText description, String applicableOccurrence,
                       IfcPropertySetDefinition[] hasPropertySets,
                       IfcRelDefinesByType[] objectTypeOf,
                       IfcRepresentationMap[] representationMaps, String tag,
                       String elementType, IfcCoilTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
