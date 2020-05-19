package buildingsmart.ifc;

public class IfcColumnType extends IfcBuildingElementType {
    private final IfcColumnTypeEnum PredefinedType;

    public IfcColumnType(IfcGloballyUniqueId globalId,
                         IfcOwnerHistory ownerHistory, IfcLabel name,
                         IfcText description, String applicableOccurrence,
                         IfcPropertySetDefinition[] hasPropertySets,
                         IfcRelDefinesByType[] objectTypeOf,
                         IfcRepresentationMap[] representationMaps, String tag,
                         String elementType, IfcColumnTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
