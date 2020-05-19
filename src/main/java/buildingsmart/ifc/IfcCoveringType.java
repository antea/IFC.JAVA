package buildingsmart.ifc;

public class IfcCoveringType extends IfcBuildingElementType {
    private final IfcCoveringTypeEnum PredefinedType;

    public IfcCoveringType(IfcGloballyUniqueId globalId,
                           IfcOwnerHistory ownerHistory, IfcLabel name,
                           IfcText description, String applicableOccurrence,
                           IfcPropertySetDefinition[] hasPropertySets,
                           IfcRelDefinesByType[] objectTypeOf,
                           IfcRepresentationMap[] representationMaps,
                           String tag, String elementType,
                           IfcCoveringTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
