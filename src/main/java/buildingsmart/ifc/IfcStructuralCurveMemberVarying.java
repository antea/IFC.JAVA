package buildingsmart.ifc;

public class IfcStructuralCurveMemberVarying extends IfcStructuralCurveMember {
    public IfcStructuralCurveMemberVarying(IfcGloballyUniqueId globalId,
                                           IfcOwnerHistory ownerHistory,
                                           IfcLabel name, IfcText description,
                                           IfcLabel objectType,
                                           IfcRelConnectsStructuralElement[] referencesElement,
                                           IfcStructuralCurveTypeEnum predefinedType) {
        super(globalId, ownerHistory, name, description, objectType,
                referencesElement, predefinedType);
    }
}
