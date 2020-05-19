package buildingsmart.ifc;

public class IfcFlowFitting extends IfcDistributionFlowElement {
    public IfcFlowFitting(IfcGloballyUniqueId globalId,
                          IfcOwnerHistory ownerHistory, IfcLabel name,
                          IfcText description, IfcLabel objectType,
                          IfcRelFlowControlElements[] hasControlElements) {
        super(globalId, ownerHistory, name, description, objectType,
                hasControlElements);
    }
}
