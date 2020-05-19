package buildingsmart.ifc;

public class IfcBuildingElementProxyType extends IfcBuildingElementType {
    private final IfcBuildingElementProxyTypeEnum PredefinedType;

    public IfcBuildingElementProxyType(IfcGloballyUniqueId globalId,
                                       IfcOwnerHistory ownerHistory,
                                       IfcLabel name, IfcText description,
                                       String applicableOccurrence,
                                       IfcPropertySetDefinition[] hasPropertySets,
                                       IfcRelDefinesByType[] objectTypeOf,
                                       IfcRepresentationMap[] representationMaps,
                                       String tag, String elementType,
                                       IfcBuildingElementProxyTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
