package buildingsmart.ifc;

public class IfcFlowTreatmentDevice extends IfcDistributionFlowElement {
    public IfcFlowTreatmentDevice(IfcGloballyUniqueId globalId,
                                  IfcOwnerHistory ownerHistory, IfcLabel name,
                                  IfcText description, IfcLabel objectType,
                                  IfcRelFlowControlElements[] hasControlElements) {
        super(globalId, ownerHistory, name, description, objectType,
                hasControlElements);
    }
}
