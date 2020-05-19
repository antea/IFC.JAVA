package buildingsmart.ifc;

public class IfcElectricalCircuit extends IfcSystem {
    public IfcElectricalCircuit(IfcGloballyUniqueId globalId,
                                IfcOwnerHistory ownerHistory, IfcLabel name,
                                IfcText description, IfcLabel objectType,
                                IfcRelAssignsToGroup isGroupedBy,
                                IfcRelServicesBuildings[] servicesBuildings) {
        super(globalId, ownerHistory, name, description, objectType,
                isGroupedBy, servicesBuildings);
    }
}
