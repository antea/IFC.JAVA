package buildingsmart.ifc;

public class IfcFastener extends IfcElementComponent {
    public IfcFastener(IfcGloballyUniqueId globalId,
                       IfcOwnerHistory ownerHistory, IfcLabel name,
                       IfcText description, IfcLabel objectType) {
        super(globalId, ownerHistory, name, description, objectType);
    }
}
