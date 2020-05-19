package buildingsmart.ifc;

public class IfcSpaceThermalLoadProperties extends IfcPropertySetDefinition {
    private final IfcRatioMeasure ApplicableValueRatio;
    private IfcThermalLoadSourceEnum ThermalLoadSource;
    private IfcPropertySourceEnum PropertySource;
    private String SourceDescription;
    private double MaximumValue;
    private double MinimumValue;
    private IfcTimeSeries ThermalLoadTimeSeriesValues;
    private String UserDefinedThermalLoadSource;
    private String UserDefinedPropertySource;
    private IfcThermalLoadTypeEnum ThermalLoadType;

    public IfcSpaceThermalLoadProperties(IfcGloballyUniqueId globalId,
                                         IfcOwnerHistory ownerHistory,
                                         IfcLabel name, IfcText description,
                                         IfcRelAssociates[] hasAssociations,
                                         IfcRelDefinesByProperties[] propertyDefinitionOf,
                                         IfcRatioMeasure applicableValueRatio) {

        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf);
        ApplicableValueRatio = applicableValueRatio;
    }
}
