package buildingsmart.ifc;

public class IfcFeatureElementAddition extends IfcFeatureElement {
    private final IfcRelProjectsElement ProjectsElements;

    public IfcFeatureElementAddition(IfcGloballyUniqueId globalId,
                                     IfcOwnerHistory ownerHistory,
                                     IfcLabel name, IfcText description,
                                     IfcLabel objectType,
                                     IfcRelProjectsElement projectsElements) {

        super(globalId, ownerHistory, name, description, objectType);
        ProjectsElements = projectsElements;
    }
}
