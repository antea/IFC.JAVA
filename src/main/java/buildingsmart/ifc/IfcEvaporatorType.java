package buildingsmart.ifc;

public class IfcEvaporatorType extends IfcEnergyConversionDeviceType {
    private final IfcEvaporatorTypeEnum PredefinedType;

    public IfcEvaporatorType(IfcGloballyUniqueId globalId,
                             IfcOwnerHistory ownerHistory, IfcLabel name,
                             IfcText description, String applicableOccurrence,
                             IfcPropertySetDefinition[] hasPropertySets,
                             IfcRelDefinesByType[] objectTypeOf,
                             IfcRepresentationMap[] representationMaps,
                             String tag, String elementType,
                             IfcEvaporatorTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
