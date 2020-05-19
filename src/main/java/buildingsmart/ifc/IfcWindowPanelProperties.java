package buildingsmart.ifc;

public class IfcWindowPanelProperties extends IfcPropertySetDefinition {
    private final IfcWindowPanelOperationEnum OperationType;
    private IfcWindowPanelPositionEnum PanelPosition;
    private IfcLengthMeasure FrameDepth;
    private IfcLengthMeasure FrameThickness;
    private IfcShapeAspect ShapeAspectStyle;

    public IfcWindowPanelProperties(IfcGloballyUniqueId globalId,
                                    IfcOwnerHistory ownerHistory, IfcLabel name,
                                    IfcText description,
                                    IfcRelAssociates[] hasAssociations,
                                    IfcRelDefinesByProperties[] propertyDefinitionOf,
                                    IfcWindowPanelOperationEnum operationType) {

        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf);
        OperationType = operationType;
    }
}
