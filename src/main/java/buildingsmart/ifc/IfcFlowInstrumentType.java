package buildingsmart.ifc;

public class IfcFlowInstrumentType extends IfcDistributionControlElementType {
    private final IfcFlowInstrumentTypeEnum PredefinedType;

    public IfcFlowInstrumentType(IfcGloballyUniqueId globalId,
                                 IfcOwnerHistory ownerHistory, IfcLabel name,
                                 IfcText description,
                                 String applicableOccurrence,
                                 IfcPropertySetDefinition[] hasPropertySets,
                                 IfcRelDefinesByType[] objectTypeOf,
                                 IfcRepresentationMap[] representationMaps,
                                 String tag, String elementType,
                                 IfcFlowInstrumentTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
