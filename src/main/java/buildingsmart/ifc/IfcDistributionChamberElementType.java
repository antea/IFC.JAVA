package buildingsmart.ifc;

public class IfcDistributionChamberElementType
        extends IfcDistributionFlowElementType {
    private final IfcDistributionChamberElementTypeEnum PredefinedType;

    public IfcDistributionChamberElementType(IfcGloballyUniqueId globalId,
                                             IfcOwnerHistory ownerHistory,
                                             IfcLabel name, IfcText description,
                                             String applicableOccurrence,
                                             IfcPropertySetDefinition[] hasPropertySets,
                                             IfcRelDefinesByType[] objectTypeOf,
                                             IfcRepresentationMap[] representationMaps,
                                             String tag, String elementType,
                                             IfcDistributionChamberElementTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
