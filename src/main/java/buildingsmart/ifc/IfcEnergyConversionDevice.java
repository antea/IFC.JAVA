package buildingsmart.ifc;

public class IfcEnergyConversionDevice extends IfcDistributionFlowElement {
    public IfcEnergyConversionDevice(IfcGloballyUniqueId globalId,
                                     IfcOwnerHistory ownerHistory,
                                     IfcLabel name, IfcText description,
                                     IfcLabel objectType,
                                     IfcRelFlowControlElements[] hasControlElements) {
        super(globalId, ownerHistory, name, description, objectType,
                hasControlElements);
    }
}
