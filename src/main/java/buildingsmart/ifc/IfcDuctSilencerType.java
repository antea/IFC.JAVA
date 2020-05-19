package buildingsmart.ifc;

public class IfcDuctSilencerType extends IfcFlowTreatmentDeviceType {
    private final IfcDuctSilencerTypeEnum PredefinedType;

    public IfcDuctSilencerType(IfcGloballyUniqueId globalId,
                               IfcOwnerHistory ownerHistory, IfcLabel name,
                               IfcText description, String applicableOccurrence,
                               IfcPropertySetDefinition[] hasPropertySets,
                               IfcRelDefinesByType[] objectTypeOf,
                               IfcRepresentationMap[] representationMaps,
                               String tag, String elementType,
                               IfcDuctSilencerTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
