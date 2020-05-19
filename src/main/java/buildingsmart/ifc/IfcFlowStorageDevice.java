package buildingsmart.ifc;

public class IfcFlowStorageDevice extends IfcDistributionFlowElement {
    public IfcFlowStorageDevice(IfcGloballyUniqueId globalId,
                                IfcOwnerHistory ownerHistory, IfcLabel name,
                                IfcText description, IfcLabel objectType,
                                IfcRelFlowControlElements[] hasControlElements) {
        super(globalId, ownerHistory, name, description, objectType,
                hasControlElements);
    }
}
