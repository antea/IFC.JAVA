package buildingsmart.ifc;

public class IfcPlateType extends IfcBuildingElementType {
    private final IfcPlateTypeEnum PredefinedType;

    public IfcPlateType(IfcGloballyUniqueId globalId,
                        IfcOwnerHistory ownerHistory, IfcLabel name,
                        IfcText description, String applicableOccurrence,
                        IfcPropertySetDefinition[] hasPropertySets,
                        IfcRelDefinesByType[] objectTypeOf,
                        IfcRepresentationMap[] representationMaps, String tag,
                        String elementType, IfcPlateTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
