package buildingsmart.ifc;

public class IfcRelOccupiesSpaces extends IfcRelAssignsToActor {
    public IfcRelOccupiesSpaces(IfcGloballyUniqueId globalId,
                                IfcOwnerHistory ownerHistory, IfcLabel name,
                                IfcText description, IfcActor relatingActor) {
        super(globalId, ownerHistory, name, description, relatingActor);
    }
}
