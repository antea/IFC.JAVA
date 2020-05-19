package buildingsmart.ifc;

public class IfcProjectionElement extends IfcFeatureElementAddition {
    public IfcProjectionElement(IfcGloballyUniqueId globalId,
                                IfcOwnerHistory ownerHistory, IfcLabel name,
                                IfcText description, IfcLabel objectType,
                                IfcRelProjectsElement projectsElements) {
        super(globalId, ownerHistory, name, description, objectType,
                projectsElements);
    }
}
