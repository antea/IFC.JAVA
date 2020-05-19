package buildingsmart.ifc;

public class IfcConstructionMaterialResource extends IfcConstructionResource {
    private final IfcActorSelect[] Suppliers;
    private double UsageRatio;

    public IfcConstructionMaterialResource(IfcGloballyUniqueId globalId,
                                           IfcOwnerHistory ownerHistory,
                                           IfcLabel name, IfcText description,
                                           IfcLabel objectType,
                                           IfcRelAssignsToResource[] resourceOf,
                                           String resourceIdentifier,
                                           IfcActorSelect[] suppliers) {

        super(globalId, ownerHistory, name, description, objectType, resourceOf,
                resourceIdentifier);
        Suppliers = suppliers;
    }
}
