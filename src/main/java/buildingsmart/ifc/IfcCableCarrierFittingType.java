package buildingsmart.ifc;

public class IfcCableCarrierFittingType extends IfcFlowFittingType {
    private final IfcCableCarrierFittingTypeEnum PredefinedType;

    public IfcCableCarrierFittingType(IfcGloballyUniqueId globalId,
                                      IfcOwnerHistory ownerHistory,
                                      IfcLabel name, IfcText description,
                                      String applicableOccurrence,
                                      IfcPropertySetDefinition[] hasPropertySets,
                                      IfcRelDefinesByType[] objectTypeOf,
                                      IfcRepresentationMap[] representationMaps,
                                      String tag, String elementType,
                                      IfcCableCarrierFittingTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
