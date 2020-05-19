package buildingsmart.ifc;

public class IfcSpaceType extends IfcSpatialStructureElementType {
    private final IfcSpaceTypeEnum PredefinedType;

    public IfcSpaceType(IfcGloballyUniqueId globalId,
                        IfcOwnerHistory ownerHistory, IfcLabel name,
                        IfcText description, String applicableOccurrence,
                        IfcPropertySetDefinition[] hasPropertySets,
                        IfcRelDefinesByType[] objectTypeOf,
                        IfcRepresentationMap[] representationMaps, String tag,
                        String elementType, IfcSpaceTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
