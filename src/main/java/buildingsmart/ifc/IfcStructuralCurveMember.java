package buildingsmart.ifc;

public class IfcStructuralCurveMember extends IfcStructuralMember {
    private final IfcStructuralCurveTypeEnum PredefinedType;

    public IfcStructuralCurveMember(IfcGloballyUniqueId globalId,
                                    IfcOwnerHistory ownerHistory, IfcLabel name,
                                    IfcText description, IfcLabel objectType,
                                    IfcRelConnectsStructuralElement[] referencesElement,
                                    IfcStructuralCurveTypeEnum predefinedType) {

        super(globalId, ownerHistory, name, description, objectType,
                referencesElement);
        PredefinedType = predefinedType;
    }
}
