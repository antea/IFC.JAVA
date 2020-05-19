package buildingsmart.ifc;

public class IfcStructuralLinearAction extends IfcStructuralAction {
    private final IfcProjectedOrTrueLengthEnum ProjectedOrTrue;

    public IfcStructuralLinearAction(IfcGloballyUniqueId globalId,
                                     IfcOwnerHistory ownerHistory,
                                     IfcLabel name, IfcText description,
                                     IfcLabel objectType,
                                     boolean destabilizingLoad,
                                     IfcProjectedOrTrueLengthEnum projectedOrTrue) {

        super(globalId, ownerHistory, name, description, objectType,
                destabilizingLoad);
        ProjectedOrTrue = projectedOrTrue;
    }
}
