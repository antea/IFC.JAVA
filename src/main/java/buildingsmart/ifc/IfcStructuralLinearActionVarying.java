package buildingsmart.ifc;

public class IfcStructuralLinearActionVarying
        extends IfcStructuralLinearAction {
    private final IfcShapeAspect VaryingAppliedLoadLocation;
    private IfcStructuralLoad[] SubsequentAppliedLoads;
    private IfcStructuralLoad[] VaryingAppliedLoads;

    public IfcStructuralLinearActionVarying(IfcGloballyUniqueId globalId,
                                            IfcOwnerHistory ownerHistory,
                                            IfcLabel name, IfcText description,
                                            IfcLabel objectType,
                                            boolean destabilizingLoad,
                                            IfcProjectedOrTrueLengthEnum projectedOrTrue,
                                            IfcShapeAspect varyingAppliedLoadLocation) {

        super(globalId, ownerHistory, name, description, objectType,
                destabilizingLoad, projectedOrTrue);
        VaryingAppliedLoadLocation = varyingAppliedLoadLocation;
    }
}
