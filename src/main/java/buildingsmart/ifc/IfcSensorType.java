package buildingsmart.ifc;

public class IfcSensorType extends IfcDistributionControlElementType {
    private final IfcSensorTypeEnum PredefinedType;

    public IfcSensorType(IfcGloballyUniqueId globalId,
                         IfcOwnerHistory ownerHistory, IfcLabel name,
                         IfcText description, String applicableOccurrence,
                         IfcPropertySetDefinition[] hasPropertySets,
                         IfcRelDefinesByType[] objectTypeOf,
                         IfcRepresentationMap[] representationMaps, String tag,
                         String elementType, IfcSensorTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
