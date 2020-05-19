package buildingsmart.ifc;

public class IfcConstructionResource extends IfcResource {
    private final String ResourceIdentifier;
    private String ResourceGroup;
    private IfcResourceConsumptionEnum ResourceConsumption;
    private IfcMeasureWithUnit BaseQuantity;

    public IfcConstructionResource(IfcGloballyUniqueId globalId,
                                   IfcOwnerHistory ownerHistory, IfcLabel name,
                                   IfcText description, IfcLabel objectType,
                                   IfcRelAssignsToResource[] resourceOf,
                                   String resourceIdentifier) {

        super(globalId, ownerHistory, name, description, objectType,
                resourceOf);
        ResourceIdentifier = resourceIdentifier;
    }
}
