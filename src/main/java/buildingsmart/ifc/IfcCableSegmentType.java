package buildingsmart.ifc;

public class IfcCableSegmentType extends IfcFlowSegmentType {
    private final IfcCableSegmentTypeEnum PredefinedType;

    public IfcCableSegmentType(IfcGloballyUniqueId globalId,
                               IfcOwnerHistory ownerHistory, IfcLabel name,
                               IfcText description, String applicableOccurrence,
                               IfcPropertySetDefinition[] hasPropertySets,
                               IfcRelDefinesByType[] objectTypeOf,
                               IfcRepresentationMap[] representationMaps,
                               String tag, String elementType,
                               IfcCableSegmentTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
