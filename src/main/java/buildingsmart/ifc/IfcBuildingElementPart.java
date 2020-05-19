package buildingsmart.ifc;

public class IfcBuildingElementPart extends IfcBuildingElementComponent {
    public IfcBuildingElementPart(IfcGloballyUniqueId globalId,
                                  IfcOwnerHistory ownerHistory, IfcLabel name,
                                  IfcText description, IfcLabel objectType) {
        super(globalId, ownerHistory, name, description, objectType);
    }
}
