package buildingsmart.ifc;

public class IfcHeatExchangerType extends IfcEnergyConversionDeviceType {
    private final IfcHeatExchangerTypeEnum PredefinedType;

    public IfcHeatExchangerType(IfcGloballyUniqueId globalId,
                                IfcOwnerHistory ownerHistory, IfcLabel name,
                                IfcText description,
                                String applicableOccurrence,
                                IfcPropertySetDefinition[] hasPropertySets,
                                IfcRelDefinesByType[] objectTypeOf,
                                IfcRepresentationMap[] representationMaps,
                                String tag, String elementType,
                                IfcHeatExchangerTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
