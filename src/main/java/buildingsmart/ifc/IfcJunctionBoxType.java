package buildingsmart.ifc;

public class IfcJunctionBoxType extends IfcFlowFittingType {
    private final IfcJunctionBoxTypeEnum PredefinedType;

    public IfcJunctionBoxType(IfcGloballyUniqueId globalId,
                              IfcOwnerHistory ownerHistory, IfcLabel name,
                              IfcText description, String applicableOccurrence,
                              IfcPropertySetDefinition[] hasPropertySets,
                              IfcRelDefinesByType[] objectTypeOf,
                              IfcRepresentationMap[] representationMaps,
                              String tag, String elementType,
                              IfcJunctionBoxTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
