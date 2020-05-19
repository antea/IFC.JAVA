package buildingsmart.ifc;

public class IfcStructuralCurveConnection extends IfcStructuralConnection {
    public IfcStructuralCurveConnection(IfcGloballyUniqueId globalId,
                                        IfcOwnerHistory ownerHistory,
                                        IfcLabel name, IfcText description,
                                        IfcLabel objectType,
                                        IfcBoundaryCondition appliedCondition) {
        super(globalId, ownerHistory, name, description, objectType,
                appliedCondition);
    }
}
