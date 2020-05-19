package buildingsmart.ifc;

public class IfcAirToAirHeatRecoveryType extends IfcEnergyConversionDeviceType {
    private final IfcAirToAirHeatRecoveryTypeEnum PredefinedType;

    public IfcAirToAirHeatRecoveryType(IfcGloballyUniqueId globalId,
                                       IfcOwnerHistory ownerHistory,
                                       IfcLabel name, IfcText description,
                                       String applicableOccurrence,
                                       IfcPropertySetDefinition[] hasPropertySets,
                                       IfcRelDefinesByType[] objectTypeOf,
                                       IfcRepresentationMap[] representationMaps,
                                       String tag, String elementType,
                                       IfcAirToAirHeatRecoveryTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
