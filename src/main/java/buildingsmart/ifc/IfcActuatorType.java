package buildingsmart.ifc;

public class IfcActuatorType extends IfcDistributionControlElementType {
    private final IfcActuatorTypeEnum PredefinedType;

    public IfcActuatorType(IfcGloballyUniqueId globalId,
                           IfcOwnerHistory ownerHistory, IfcLabel name,
                           IfcText description, String applicableOccurrence,
                           IfcPropertySetDefinition[] hasPropertySets,
                           IfcRelDefinesByType[] objectTypeOf,
                           IfcRepresentationMap[] representationMaps,
                           String tag, String elementType,
                           IfcActuatorTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
