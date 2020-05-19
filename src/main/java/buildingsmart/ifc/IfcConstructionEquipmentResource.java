package buildingsmart.ifc;

public class IfcConstructionEquipmentResource extends IfcConstructionResource {
    public IfcConstructionEquipmentResource(IfcGloballyUniqueId globalId,
                                            IfcOwnerHistory ownerHistory,
                                            IfcLabel name, IfcText description,
                                            IfcLabel objectType,
                                            IfcRelAssignsToResource[] resourceOf,
                                            String resourceIdentifier) {
        super(globalId, ownerHistory, name, description, objectType, resourceOf,
                resourceIdentifier);
    }
}
