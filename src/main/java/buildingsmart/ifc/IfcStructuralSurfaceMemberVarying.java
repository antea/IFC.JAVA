package buildingsmart.ifc;

public class IfcStructuralSurfaceMemberVarying
        extends IfcStructuralSurfaceMember {
    private final IfcLengthMeasure[] SubsequentThickness;
    private IfcShapeAspect VaryingThicknessLocation;
    private IfcLengthMeasure[] VaryingThickness;

    public IfcStructuralSurfaceMemberVarying(IfcGloballyUniqueId globalId,
                                             IfcOwnerHistory ownerHistory,
                                             IfcLabel name, IfcText description,
                                             IfcLabel objectType,
                                             IfcRelConnectsStructuralElement[] referencesElement,
                                             IfcStructuralSurfaceTypeEnum predefinedType,
                                             IfcLengthMeasure[] subsequentThickness) {

        super(globalId, ownerHistory, name, description, objectType,
                referencesElement, predefinedType);
        SubsequentThickness = subsequentThickness;
    }
}
