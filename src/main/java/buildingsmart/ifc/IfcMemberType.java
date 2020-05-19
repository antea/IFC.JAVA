package buildingsmart.ifc;

public class IfcMemberType extends IfcBuildingElementType {
    private final IfcMemberTypeEnum PredefinedType;

    public IfcMemberType(IfcGloballyUniqueId globalId,
                         IfcOwnerHistory ownerHistory, IfcLabel name,
                         IfcText description, String applicableOccurrence,
                         IfcPropertySetDefinition[] hasPropertySets,
                         IfcRelDefinesByType[] objectTypeOf,
                         IfcRepresentationMap[] representationMaps, String tag,
                         String elementType, IfcMemberTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
