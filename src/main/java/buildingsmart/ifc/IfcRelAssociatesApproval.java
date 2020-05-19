package buildingsmart.ifc;

public class IfcRelAssociatesApproval extends IfcRelAssociates {
    private final IfcApproval RelatingApproval;

    public IfcRelAssociatesApproval(IfcGloballyUniqueId globalId,
                                    IfcOwnerHistory ownerHistory, IfcLabel name,
                                    IfcText description,
                                    IfcRoot[] relatedObjects,
                                    IfcApproval relatingApproval) {

        super(globalId, ownerHistory, name, description, relatedObjects);
        RelatingApproval = relatingApproval;
    }
}
