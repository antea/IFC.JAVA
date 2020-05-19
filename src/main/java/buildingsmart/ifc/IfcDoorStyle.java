package buildingsmart.ifc;

public class IfcDoorStyle extends IfcTypeProduct {
    private final IfcDoorStyleOperationEnum OperationType;
    private IfcDoorStyleConstructionEnum ConstructionType;
    private boolean ParameterTakesPrecedence;
    private boolean Sizeable;

    public IfcDoorStyle(IfcGloballyUniqueId globalId,
                        IfcOwnerHistory ownerHistory, IfcLabel name,
                        IfcText description, String applicableOccurrence,
                        IfcPropertySetDefinition[] hasPropertySets,
                        IfcRelDefinesByType[] objectTypeOf,
                        IfcRepresentationMap[] representationMaps, String tag,
                        IfcDoorStyleOperationEnum operationType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag);
        OperationType = operationType;
    }
}
