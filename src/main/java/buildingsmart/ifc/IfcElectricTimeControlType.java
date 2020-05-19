package buildingsmart.ifc;

public class IfcElectricTimeControlType extends IfcFlowControllerType {
    private final IfcElectricTimeControlTypeEnum PredefinedType;

    public IfcElectricTimeControlType(IfcGloballyUniqueId globalId,
                                      IfcOwnerHistory ownerHistory,
                                      IfcLabel name, IfcText description,
                                      String applicableOccurrence,
                                      IfcPropertySetDefinition[] hasPropertySets,
                                      IfcRelDefinesByType[] objectTypeOf,
                                      IfcRepresentationMap[] representationMaps,
                                      String tag, String elementType,
                                      IfcElectricTimeControlTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
