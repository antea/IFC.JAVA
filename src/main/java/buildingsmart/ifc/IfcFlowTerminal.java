package buildingsmart.ifc;

public class IfcFlowTerminal extends IfcDistributionFlowElement {
    public IfcFlowTerminal(IfcGloballyUniqueId globalId,
                           IfcOwnerHistory ownerHistory, IfcLabel name,
                           IfcText description, IfcLabel objectType,
                           IfcRelFlowControlElements[] hasControlElements) {
        super(globalId, ownerHistory, name, description, objectType,
                hasControlElements);
    }
}
