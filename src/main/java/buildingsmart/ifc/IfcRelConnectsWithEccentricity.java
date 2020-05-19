package buildingsmart.ifc;

public class IfcRelConnectsWithEccentricity
        extends IfcRelConnectsStructuralMember {
    private final IfcConnectionGeometry ConnectionConstraint;

    public IfcRelConnectsWithEccentricity(IfcGloballyUniqueId globalId,
                                          IfcOwnerHistory ownerHistory,
                                          IfcLabel name, IfcText description,
                                          IfcStructuralMember relatingStructuralMember,
                                          IfcConnectionGeometry connectionConstraint) {
        super(globalId, ownerHistory, name, description,
                relatingStructuralMember);
        ConnectionConstraint = connectionConstraint;
    }
}
