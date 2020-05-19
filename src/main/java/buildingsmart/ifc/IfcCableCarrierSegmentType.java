package buildingsmart.ifc;

public class IfcCableCarrierSegmentType extends IfcFlowSegmentType {
    private final IfcCableCarrierSegmentTypeEnum PredefinedType;

    public IfcCableCarrierSegmentType(IfcGloballyUniqueId globalId,
                                      IfcOwnerHistory ownerHistory,
                                      IfcLabel name, IfcText description,
                                      String applicableOccurrence,
                                      IfcPropertySetDefinition[] hasPropertySets,
                                      IfcRelDefinesByType[] objectTypeOf,
                                      IfcRepresentationMap[] representationMaps,
                                      String tag, String elementType,
                                      IfcCableCarrierSegmentTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
