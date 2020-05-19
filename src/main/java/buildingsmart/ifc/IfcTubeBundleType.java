package buildingsmart.ifc;

public class IfcTubeBundleType extends IfcEnergyConversionDeviceType {
    private final IfcTubeBundleTypeEnum PredefinedType;

    public IfcTubeBundleType(IfcGloballyUniqueId globalId,
                             IfcOwnerHistory ownerHistory, IfcLabel name,
                             IfcText description, String applicableOccurrence,
                             IfcPropertySetDefinition[] hasPropertySets,
                             IfcRelDefinesByType[] objectTypeOf,
                             IfcRepresentationMap[] representationMaps,
                             String tag, String elementType,
                             IfcTubeBundleTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
