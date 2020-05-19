package buildingsmart.ifc;

public class IfcSubContractResource extends IfcConstructionResource {
    private final IfcActorSelect SubContractor;
    private String JobDescription;

    public IfcSubContractResource(IfcGloballyUniqueId globalId,
                                  IfcOwnerHistory ownerHistory, IfcLabel name,
                                  IfcText description, IfcLabel objectType,
                                  IfcRelAssignsToResource[] resourceOf,
                                  String resourceIdentifier,
                                  IfcActorSelect subContractor) {

        super(globalId, ownerHistory, name, description, objectType, resourceOf,
                resourceIdentifier);
        SubContractor = subContractor;
    }
}
