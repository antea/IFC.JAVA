package buildingsmart.ifc;

public class IfcElectricGeneratorType extends IfcEnergyConversionDeviceType {
    private final IfcElectricGeneratorTypeEnum PredefinedType;

    public IfcElectricGeneratorType(IfcGloballyUniqueId globalId,
                                    IfcOwnerHistory ownerHistory, IfcLabel name,
                                    IfcText description,
                                    String applicableOccurrence,
                                    IfcPropertySetDefinition[] hasPropertySets,
                                    IfcRelDefinesByType[] objectTypeOf,
                                    IfcRepresentationMap[] representationMaps,
                                    String tag, String elementType,
                                    IfcElectricGeneratorTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
