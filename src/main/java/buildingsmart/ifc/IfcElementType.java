package buildingsmart.ifc;

public class IfcElementType extends IfcTypeProduct {
    private final String ElementType;

    public IfcElementType(IfcGloballyUniqueId globalId,
                          IfcOwnerHistory ownerHistory, IfcLabel name,
                          IfcText description, String applicableOccurrence,
                          IfcPropertySetDefinition[] hasPropertySets,
                          IfcRelDefinesByType[] objectTypeOf,
                          IfcRepresentationMap[] representationMaps, String tag,
                          String elementType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag);
        ElementType = elementType;
    }
}
