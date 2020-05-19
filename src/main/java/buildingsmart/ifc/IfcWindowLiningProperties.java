package buildingsmart.ifc;

public class IfcWindowLiningProperties extends IfcPropertySetDefinition {
    private final IfcLengthMeasure LiningDepth;
    private IfcLengthMeasure LiningThickness;
    private IfcLengthMeasure TransomThickness;
    private IfcLengthMeasure MullionThickness;
    private IfcRatioMeasure FirstTransomOffset;
    private IfcRatioMeasure SecondTransomOffset;
    private IfcRatioMeasure FirstMullionOffset;
    private IfcRatioMeasure SecondMullionOffset;
    private IfcShapeAspect ShapeAspectStyle;

    public IfcWindowLiningProperties(IfcGloballyUniqueId globalId,
                                     IfcOwnerHistory ownerHistory,
                                     IfcLabel name, IfcText description,
                                     IfcRelAssociates[] hasAssociations,
                                     IfcRelDefinesByProperties[] propertyDefinitionOf,
                                     IfcLengthMeasure liningDepth) {

        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf);
        LiningDepth = liningDepth;
    }
}
