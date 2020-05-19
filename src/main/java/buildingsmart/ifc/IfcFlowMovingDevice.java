package buildingsmart.ifc;

public class IfcFlowMovingDevice extends IfcDistributionFlowElement {
    public IfcFlowMovingDevice(IfcGloballyUniqueId globalId,
                               IfcOwnerHistory ownerHistory, IfcLabel name,
                               IfcText description, IfcLabel objectType,
                               IfcRelFlowControlElements[] hasControlElements) {
        super(globalId, ownerHistory, name, description, objectType,
                hasControlElements);
    }
}
