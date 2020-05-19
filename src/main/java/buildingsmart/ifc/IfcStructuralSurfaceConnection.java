package buildingsmart.ifc;

public class IfcStructuralSurfaceConnection extends IfcStructuralConnection {
    public IfcStructuralSurfaceConnection(IfcGloballyUniqueId globalId,
                                          IfcOwnerHistory ownerHistory,
                                          IfcLabel name, IfcText description,
                                          IfcLabel objectType,
                                          IfcBoundaryCondition appliedCondition) {
        super(globalId, ownerHistory, name, description, objectType,
                appliedCondition);
    }
}
