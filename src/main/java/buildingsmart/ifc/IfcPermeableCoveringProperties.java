package buildingsmart.ifc;

public class IfcPermeableCoveringProperties extends IfcPropertySetDefinition {
    private final IfcPermeableCoveringOperationEnum OperationType;
    private IfcWindowPanelPositionEnum PanelPosition;
    private IfcLengthMeasure FrameDepth;
    private IfcLengthMeasure FrameThickness;
    private IfcShapeAspect ShapeAspectStyle;

    public IfcPermeableCoveringProperties(IfcGloballyUniqueId globalId,
                                          IfcOwnerHistory ownerHistory,
                                          IfcLabel name, IfcText description,
                                          IfcRelAssociates[] hasAssociations,
                                          IfcRelDefinesByProperties[] propertyDefinitionOf,
                                          IfcPermeableCoveringOperationEnum operationType) {
        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf);
        OperationType = operationType;
    }
}
