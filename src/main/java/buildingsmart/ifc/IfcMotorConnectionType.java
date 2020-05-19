package buildingsmart.ifc;

public class IfcMotorConnectionType extends IfcEnergyConversionDeviceType {
    private final IfcMotorConnectionTypeEnum PredefinedType;

    public IfcMotorConnectionType(IfcGloballyUniqueId globalId,
                                  IfcOwnerHistory ownerHistory, IfcLabel name,
                                  IfcText description,
                                  String applicableOccurrence,
                                  IfcPropertySetDefinition[] hasPropertySets,
                                  IfcRelDefinesByType[] objectTypeOf,
                                  IfcRepresentationMap[] representationMaps,
                                  String tag, String elementType,
                                  IfcMotorConnectionTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
