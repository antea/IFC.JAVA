package buildingsmart.ifc;

public class IfcEvaporativeCoolerType extends IfcEnergyConversionDeviceType {
    private final IfcEvaporativeCoolerTypeEnum PredefinedType;

    public IfcEvaporativeCoolerType(IfcGloballyUniqueId globalId,
                                    IfcOwnerHistory ownerHistory, IfcLabel name,
                                    IfcText description,
                                    String applicableOccurrence,
                                    IfcPropertySetDefinition[] hasPropertySets,
                                    IfcRelDefinesByType[] objectTypeOf,
                                    IfcRepresentationMap[] representationMaps,
                                    String tag, String elementType,
                                    IfcEvaporativeCoolerTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
