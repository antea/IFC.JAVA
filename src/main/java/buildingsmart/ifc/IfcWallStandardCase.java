package buildingsmart.ifc;

public class IfcWallStandardCase extends IfcWall {
    public IfcWallStandardCase(IfcGloballyUniqueId globalId,
                               IfcOwnerHistory ownerHistory, IfcLabel name,
                               IfcText description, IfcLabel objectType) {
        super(globalId, ownerHistory, name, description, objectType);
    }
}
