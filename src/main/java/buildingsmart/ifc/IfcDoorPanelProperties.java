package buildingsmart.ifc;

public class IfcDoorPanelProperties extends IfcPropertySetDefinition {
    private final IfcLengthMeasure PanelDepth;
    private IfcDoorPanelOperationEnum PanelOperation;
    private IfcRatioMeasure PanelWidth;
    private IfcDoorPanelPositionEnum PanelPosition;
    private IfcShapeAspect ShapeAspectStyle;

    public IfcDoorPanelProperties(IfcGloballyUniqueId globalId,
                                  IfcOwnerHistory ownerHistory, IfcLabel name,
                                  IfcText description,
                                  IfcRelAssociates[] hasAssociations,
                                  IfcRelDefinesByProperties[] propertyDefinitionOf,
                                  IfcLengthMeasure panelDepth) {

        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf);
        PanelDepth = panelDepth;
    }
}
