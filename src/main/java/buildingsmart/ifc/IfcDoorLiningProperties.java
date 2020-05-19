package buildingsmart.ifc;

public class IfcDoorLiningProperties extends IfcPropertySetDefinition {
    private final IfcLengthMeasure LiningDepth;
    private IfcLengthMeasure LiningThickness;
    private IfcLengthMeasure ThresholdDepth;
    private IfcLengthMeasure ThresholdThickness;
    private IfcLengthMeasure TransomThickness;
    private double TransomOffset;
    private double LiningOffset;
    private double ThresholdOffset;
    private IfcLengthMeasure CasingThickness;
    private IfcLengthMeasure CasingDepth;
    private IfcShapeAspect ShapeAspectStyle;

    public IfcDoorLiningProperties(IfcGloballyUniqueId globalId,
                                   IfcOwnerHistory ownerHistory, IfcLabel name,
                                   IfcText description,
                                   IfcRelAssociates[] hasAssociations,
                                   IfcRelDefinesByProperties[] propertyDefinitionOf,
                                   IfcLengthMeasure liningDepth) {

        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf);
        LiningDepth = liningDepth;
    }
}
