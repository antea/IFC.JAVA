package buildingsmart.ifc;

public class IfcStructuralPointConnection extends IfcStructuralConnection {
    public IfcStructuralPointConnection(IfcGloballyUniqueId globalId,
                                        IfcOwnerHistory ownerHistory,
                                        IfcLabel name, IfcText description,
                                        IfcLabel objectType,
                                        IfcBoundaryCondition appliedCondition) {
        super(globalId, ownerHistory, name, description, objectType,
                appliedCondition);
    }
}
