package buildingsmart.ifc;

public class IfcVibrationIsolatorType extends IfcDiscreteAccessoryType {
    private final IfcVibrationIsolatorTypeEnum PredefinedType;

    public IfcVibrationIsolatorType(IfcGloballyUniqueId globalId,
                                    IfcOwnerHistory ownerHistory, IfcLabel name,
                                    IfcText description,
                                    String applicableOccurrence,
                                    IfcPropertySetDefinition[] hasPropertySets,
                                    IfcRelDefinesByType[] objectTypeOf,
                                    IfcRepresentationMap[] representationMaps,
                                    String tag, String elementType,
                                    IfcVibrationIsolatorTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, applicableOccurrence,
                hasPropertySets, objectTypeOf, representationMaps, tag,
                elementType);
        PredefinedType = predefinedType;
    }
}
