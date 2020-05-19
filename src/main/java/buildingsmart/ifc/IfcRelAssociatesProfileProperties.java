package buildingsmart.ifc;

public class IfcRelAssociatesProfileProperties extends IfcRelAssociates {
    private final IfcProfileProperties RelatingProfileProperties;
    private IfcShapeAspect ProfileSectionLocation;
    private IfcOrientationSelect ProfileOrientation;

    public IfcRelAssociatesProfileProperties(IfcGloballyUniqueId globalId,
                                             IfcOwnerHistory ownerHistory,
                                             IfcLabel name, IfcText description,
                                             IfcRoot[] relatedObjects,
                                             IfcProfileProperties relatingProfileProperties) {
        super(globalId, ownerHistory, name, description, relatedObjects);
        RelatingProfileProperties = relatingProfileProperties;
    }
}
