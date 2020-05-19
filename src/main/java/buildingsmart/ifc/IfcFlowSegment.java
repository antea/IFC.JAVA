package buildingsmart.ifc;

public class IfcFlowSegment extends IfcDistributionFlowElement {
    public IfcFlowSegment(IfcGloballyUniqueId globalId,
                          IfcOwnerHistory ownerHistory, IfcLabel name,
                          IfcText description, IfcLabel objectType,
                          IfcRelFlowControlElements[] hasControlElements) {
        super(globalId, ownerHistory, name, description, objectType,
                hasControlElements);
    }
}
