package buildingsmart.ifc;

public class IfcElectricApplianceType extends IfcFlowTerminalType {
    private final IfcElectricApplianceTypeEnum PredefinedType;

    public IfcElectricApplianceType(IfcGloballyUniqueId globalId,
                                    IfcOwnerHistory ownerHistory, IfcLabel name,
                                    IfcText description,
                                    String applicableOccurrence,
                                    IfcPropertySetDefinition[] hasPropertySets,
                                    IfcRelDefinesByType[] objectTypeOf,
                                    IfcRepresentationMap[] representationMaps,
                                    String tag, String elementType,
                                    IfcElectricApplianceTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
