package buildingsmart.ifc;

public class IfcFlowMeterType extends IfcFlowControllerType {
    private final IfcFlowMeterTypeEnum PredefinedType;

    public IfcFlowMeterType(IfcGloballyUniqueId globalId,
                            IfcOwnerHistory ownerHistory, IfcLabel name,
                            IfcText description, String applicableOccurrence,
                            IfcPropertySetDefinition[] hasPropertySets,
                            IfcRelDefinesByType[] objectTypeOf,
                            IfcRepresentationMap[] representationMaps,
                            String tag, String elementType,
                            IfcFlowMeterTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
