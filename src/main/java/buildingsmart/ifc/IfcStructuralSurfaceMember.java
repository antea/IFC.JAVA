package buildingsmart.ifc;

public class IfcStructuralSurfaceMember extends IfcStructuralMember {
    private final IfcStructuralSurfaceTypeEnum PredefinedType;
    private IfcLengthMeasure Thickness;

    public IfcStructuralSurfaceMember(IfcGloballyUniqueId globalId,
                                      IfcOwnerHistory ownerHistory,
                                      IfcLabel name, IfcText description,
                                      IfcLabel objectType,
                                      IfcRelConnectsStructuralElement[] referencesElement,
                                      IfcStructuralSurfaceTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, objectType,
                referencesElement);
        PredefinedType = predefinedType;
    }
}
