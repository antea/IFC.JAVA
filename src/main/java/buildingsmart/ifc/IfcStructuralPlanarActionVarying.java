package buildingsmart.ifc;

public class IfcStructuralPlanarActionVarying
        extends IfcStructuralPlanarAction {
    private final IfcShapeAspect VaryingAppliedLoadLocation;
    private IfcStructuralLoad[] SubsequentAppliedLoads;
    private IfcStructuralLoad[] VaryingAppliedLoads;

    public IfcStructuralPlanarActionVarying(IfcGloballyUniqueId globalId,
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
