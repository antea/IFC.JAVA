package buildingsmart.ifc;

public class IfcFurnitureType extends IfcFurnishingElementType {
    private final IfcAssemblyPlaceEnum AssemblyPlace;

    public IfcFurnitureType(IfcGloballyUniqueId globalId,
                            IfcOwnerHistory ownerHistory, IfcLabel name,
                            IfcText description, String applicableOccurrence,
                            IfcPropertySetDefinition[] hasPropertySets,
                            IfcRelDefinesByType[] objectTypeOf,
                            IfcRepresentationMap[] representationMaps,
                            String tag, String elementType,
                            IfcAssemblyPlaceEnum assemblyPlace) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        AssemblyPlace = assemblyPlace;
    }
}
