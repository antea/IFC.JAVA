package buildingsmart.ifc;

public class IfcRailingType extends IfcBuildingElementType {
    private final IfcRailingTypeEnum PredefinedType;

    public IfcRailingType(IfcGloballyUniqueId globalId,
                          IfcOwnerHistory ownerHistory, IfcLabel name,
                          IfcText description, String applicableOccurrence,
                          IfcPropertySetDefinition[] hasPropertySets,
                          IfcRelDefinesByType[] objectTypeOf,
                          IfcRepresentationMap[] representationMaps, String tag,
                          String elementType,
                          IfcRailingTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
