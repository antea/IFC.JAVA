package buildingsmart.ifc;

public class IfcSpatialStructureElementType extends IfcElementType {
    public IfcSpatialStructureElementType(IfcGloballyUniqueId globalId,
                                          IfcOwnerHistory ownerHistory,
                                          IfcLabel name, IfcText description,
                                          String applicableOccurrence,
                                          IfcPropertySetDefinition[] hasPropertySets,
                                          IfcRelDefinesByType[] objectTypeOf,
                                          IfcRepresentationMap[] representationMaps,
                                          String tag, String elementType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
    }
}
