package buildingsmart.ifc;

public class IfcTransportElementType extends IfcElementType {
    private final IfcTransportElementTypeEnum PredefinedType;

    public IfcTransportElementType(IfcGloballyUniqueId globalId,
                                   IfcOwnerHistory ownerHistory, IfcLabel name,
                                   IfcText description,
                                   String applicableOccurrence,
                                   IfcPropertySetDefinition[] hasPropertySets,
                                   IfcRelDefinesByType[] objectTypeOf,
                                   IfcRepresentationMap[] representationMaps,
                                   String tag, String elementType,
                                   IfcTransportElementTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
