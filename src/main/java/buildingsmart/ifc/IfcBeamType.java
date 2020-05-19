package buildingsmart.ifc;

public class IfcBeamType extends IfcBuildingElementType {
    private final IfcBeamTypeEnum PredefinedType;

    public IfcBeamType(IfcGloballyUniqueId globalId,
                       IfcOwnerHistory ownerHistory, IfcLabel name,
                       IfcText description, String applicableOccurrence,
                       IfcPropertySetDefinition[] hasPropertySets,
                       IfcRelDefinesByType[] objectTypeOf,
                       IfcRepresentationMap[] representationMaps, String tag,
                       String elementType, IfcBeamTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
