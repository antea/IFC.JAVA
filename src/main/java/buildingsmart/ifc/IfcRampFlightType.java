package buildingsmart.ifc;

public class IfcRampFlightType extends IfcBuildingElementType {
    private final IfcRampFlightTypeEnum PredefinedType;

    public IfcRampFlightType(IfcGloballyUniqueId globalId,
                             IfcOwnerHistory ownerHistory, IfcLabel name,
                             IfcText description, String applicableOccurrence,
                             IfcPropertySetDefinition[] hasPropertySets,
                             IfcRelDefinesByType[] objectTypeOf,
                             IfcRepresentationMap[] representationMaps,
                             String tag, String elementType,
                             IfcRampFlightTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
