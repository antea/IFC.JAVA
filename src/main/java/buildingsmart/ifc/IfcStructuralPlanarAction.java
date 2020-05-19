package buildingsmart.ifc;

public class IfcStructuralPlanarAction extends IfcStructuralAction {
    private final IfcProjectedOrTrueLengthEnum ProjectedOrTrue;

    public IfcStructuralPlanarAction(IfcGloballyUniqueId globalId,
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
