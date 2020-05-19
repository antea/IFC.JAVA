package buildingsmart.ifc;

public class IfcDuctSegmentType extends IfcFlowSegmentType {
    private final IfcDuctSegmentTypeEnum PredefinedType;

    public IfcDuctSegmentType(IfcGloballyUniqueId globalId,
                              IfcOwnerHistory ownerHistory, IfcLabel name,
                              IfcText description, String applicableOccurrence,
                              IfcPropertySetDefinition[] hasPropertySets,
                              IfcRelDefinesByType[] objectTypeOf,
                              IfcRepresentationMap[] representationMaps,
                              String tag, String elementType,
                              IfcDuctSegmentTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
