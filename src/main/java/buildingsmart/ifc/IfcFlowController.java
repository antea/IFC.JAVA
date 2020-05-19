package buildingsmart.ifc;

public class IfcFlowController extends IfcDistributionFlowElement {
    public IfcFlowController(IfcGloballyUniqueId globalId,
                             IfcOwnerHistory ownerHistory, IfcLabel name,
                             IfcText description, IfcLabel objectType,
                             IfcRelFlowControlElements[] hasControlElements) {
        super(globalId, ownerHistory, name, description, objectType,
                hasControlElements);
    }
}
