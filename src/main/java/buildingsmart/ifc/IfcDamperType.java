package buildingsmart.ifc;

public class IfcDamperType extends IfcFlowControllerType {
    private final IfcDamperTypeEnum PredefinedType;

    public IfcDamperType(IfcGloballyUniqueId globalId,
                         IfcOwnerHistory ownerHistory, IfcLabel name,
                         IfcText description, String applicableOccurrence,
                         IfcPropertySetDefinition[] hasPropertySets,
                         IfcRelDefinesByType[] objectTypeOf,
                         IfcRepresentationMap[] representationMaps, String tag,
                         String elementType, IfcDamperTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
