package buildingsmart.ifc;

public class IfcDistributionChamberElement extends IfcDistributionFlowElement {
    public IfcDistributionChamberElement(IfcGloballyUniqueId globalId,
                                         IfcOwnerHistory ownerHistory,
                                         IfcLabel name, IfcText description,
                                         IfcLabel objectType,
                                         IfcRelFlowControlElements[] hasControlElements) {
        super(globalId, ownerHistory, name, description, objectType,
                hasControlElements);
    }
}
