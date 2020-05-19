package buildingsmart.ifc;

public class IfcElectricDistributionPoint extends IfcFlowController {
    private final IfcElectricDistributionPointFunctionEnum
            DistributionPointFunction;
    private String UserDefinedFunction;

    public IfcElectricDistributionPoint(IfcGloballyUniqueId globalId,
                                        IfcOwnerHistory ownerHistory,
                                        IfcLabel name, IfcText description,
                                        IfcLabel objectType,
                                        IfcRelFlowControlElements[] hasControlElements,
                                        IfcElectricDistributionPointFunctionEnum distributionPointFunction) {

        super(globalId, ownerHistory, name, description, objectType,
                hasControlElements);
        DistributionPointFunction = distributionPointFunction;
    }
}
