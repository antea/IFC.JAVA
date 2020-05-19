package buildingsmart.ifc;

public class IfcDistributionControlElement extends IfcDistributionElement {
    private final String ControlElementId;
    private IfcRelFlowControlElements[] AssignedToFlowElement;

    public IfcDistributionControlElement(IfcGloballyUniqueId globalId,
                                         IfcOwnerHistory ownerHistory,
                                         IfcLabel name, IfcText description,
                                         IfcLabel objectType,
                                         String controlElementId) {

        super(globalId, ownerHistory, name, description, objectType);
        ControlElementId = controlElementId;
    }
}
