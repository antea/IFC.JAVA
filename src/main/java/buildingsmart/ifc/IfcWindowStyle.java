package buildingsmart.ifc;

public class IfcWindowStyle extends IfcTypeProduct {
    private final IfcWindowStyleConstructionEnum ConstructionType;
    private IfcWindowStyleOperationEnum OperationType;
    private boolean ParameterTakesPrecedence;
    private boolean Sizeable;

    public IfcWindowStyle(IfcGloballyUniqueId globalId,
                          IfcOwnerHistory ownerHistory, IfcLabel name,
                          IfcText description, String applicableOccurrence,
                          IfcPropertySetDefinition[] hasPropertySets,
                          IfcRelDefinesByType[] objectTypeOf,
                          IfcRepresentationMap[] representationMaps, String tag,
                          IfcWindowStyleConstructionEnum constructionType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag);
        ConstructionType = constructionType;
    }
}
