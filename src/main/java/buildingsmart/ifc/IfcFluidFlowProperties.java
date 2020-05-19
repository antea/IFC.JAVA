package buildingsmart.ifc;

public class IfcFluidFlowProperties extends IfcPropertySetDefinition {
    private final IfcPropertySourceEnum PropertySource;
    private IfcTimeSeries FlowConditionTimeSeries;
    private IfcTimeSeries VelocityTimeSeries;
    private IfcTimeSeries FlowrateTimeSeries;
    private IfcMaterial Fluid;
    private IfcTimeSeries PressureTimeSeries;
    private String UserDefinedPropertySource;
    private double TemperatureSingleValue;
    private double WetBulbTemperatureSingleValue;
    private IfcTimeSeries WetBulbTemperatureTimeSeries;
    private IfcTimeSeries TemperatureTimeSeries;
    private IfcDerivedMeasureValue FlowrateSingleValue;
    private IfcRatioMeasure FlowConditionSingleValue;
    private double VelocitySingleValue;
    private double PressureSingleValue;

    public IfcFluidFlowProperties(IfcGloballyUniqueId globalId,
                                  IfcOwnerHistory ownerHistory, IfcLabel name,
                                  IfcText description,
                                  IfcRelAssociates[] hasAssociations,
                                  IfcRelDefinesByProperties[] propertyDefinitionOf,
                                  IfcPropertySourceEnum propertySource) {

        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf);
        PropertySource = propertySource;
    }
}
