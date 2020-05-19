package buildingsmart.ifc;

public class IfcDistributionFlowElement extends IfcDistributionElement {
    private final IfcRelFlowControlElements[] HasControlElements;

    public IfcDistributionFlowElement(IfcGloballyUniqueId globalId,
                                      IfcOwnerHistory ownerHistory,
                                      IfcLabel name, IfcText description,
                                      IfcLabel objectType,
                                      IfcRelFlowControlElements[] hasControlElements) {

        super(globalId, ownerHistory, name, description, objectType);
        HasControlElements = hasControlElements;
    }
}
