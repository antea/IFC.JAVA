package buildingsmart.ifc;

public class IfcServiceLifeFactor extends IfcPropertySetDefinition {
    private final IfcServiceLifeFactorTypeEnum PredefinedType;
    private IfcMeasureValue UpperValue;
    private IfcMeasureValue MostUsedValue;
    private IfcMeasureValue LowerValue;

    public IfcServiceLifeFactor(IfcGloballyUniqueId globalId,
                                IfcOwnerHistory ownerHistory, IfcLabel name,
                                IfcText description,
                                IfcRelAssociates[] hasAssociations,
                                IfcRelDefinesByProperties[] propertyDefinitionOf,
                                IfcServiceLifeFactorTypeEnum predefinedType) {

        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf);
        PredefinedType = predefinedType;
    }
}
