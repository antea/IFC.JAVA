package buildingsmart.ifc;

public class IfcElectricHeaterType extends IfcFlowTerminalType {
    private final IfcElectricHeaterTypeEnum PredefinedType;

    public IfcElectricHeaterType(IfcGloballyUniqueId globalId,
                                 IfcOwnerHistory ownerHistory, IfcLabel name,
                                 IfcText description,
                                 String applicableOccurrence,
                                 IfcPropertySetDefinition[] hasPropertySets,
                                 IfcRelDefinesByType[] objectTypeOf,
                                 IfcRepresentationMap[] representationMaps,
                                 String tag, String elementType,
                                 IfcElectricHeaterTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
