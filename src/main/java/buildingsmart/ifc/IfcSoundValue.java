package buildingsmart.ifc;

public class IfcSoundValue extends IfcPropertySetDefinition {
    private final IfcTimeSeries SoundLevelTimeSeries;
    private double Frequency;
    private IfcDerivedMeasureValue SoundLevelSingleValue;

    public IfcSoundValue(IfcGloballyUniqueId globalId,
                         IfcOwnerHistory ownerHistory, IfcLabel name,
                         IfcText description,
                         IfcRelAssociates[] hasAssociations,
                         IfcRelDefinesByProperties[] propertyDefinitionOf,
                         IfcTimeSeries soundLevelTimeSeries) {

        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf);
        SoundLevelTimeSeries = soundLevelTimeSeries;
    }
}
