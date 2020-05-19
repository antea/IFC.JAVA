package buildingsmart.ifc;

public class IfcStructuralPointAction extends IfcStructuralAction {
    public IfcStructuralPointAction(IfcGloballyUniqueId globalId,
                                    IfcOwnerHistory ownerHistory, IfcLabel name,
                                    IfcText description, IfcLabel objectType,
                                    boolean destabilizingLoad) {
        super(globalId, ownerHistory, name, description, objectType,
                destabilizingLoad);
    }
}
