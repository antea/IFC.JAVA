package buildingsmart.ifc;

public class IfcStructuralPointReaction extends IfcStructuralReaction {
    public IfcStructuralPointReaction(IfcGloballyUniqueId globalId,
                                      IfcOwnerHistory ownerHistory,
                                      IfcLabel name, IfcText description,
                                      IfcLabel objectType,
                                      IfcStructuralAction[] causes) {
        super(globalId, ownerHistory, name, description, objectType, causes);
    }
}
