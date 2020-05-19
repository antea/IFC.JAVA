package buildingsmart.ifc;

public class IfcAlarmType extends IfcDistributionControlElementType {
    private final IfcAlarmTypeEnum PredefinedType;

    public IfcAlarmType(IfcGloballyUniqueId globalId,
                        IfcOwnerHistory ownerHistory, IfcLabel name,
                        IfcText description, String applicableOccurrence,
                        IfcPropertySetDefinition[] hasPropertySets,
                        IfcRelDefinesByType[] objectTypeOf,
                        IfcRepresentationMap[] representationMaps, String tag,
                        String elementType, IfcAlarmTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
