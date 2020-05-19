package buildingsmart.ifc;

public class IfcLightFixtureType extends IfcFlowTerminalType {
    private final IfcLightFixtureTypeEnum PredefinedType;

    public IfcLightFixtureType(IfcGloballyUniqueId globalId,
                               IfcOwnerHistory ownerHistory, IfcLabel name,
                               IfcText description, String applicableOccurrence,
                               IfcPropertySetDefinition[] hasPropertySets,
                               IfcRelDefinesByType[] objectTypeOf,
                               IfcRepresentationMap[] representationMaps,
                               String tag, String elementType,
                               IfcLightFixtureTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
