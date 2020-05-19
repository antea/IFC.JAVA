package buildingsmart.ifc;

public class IfcSystem extends IfcGroup {
    private final IfcRelServicesBuildings[] ServicesBuildings;

    public IfcSystem(IfcGloballyUniqueId globalId, IfcOwnerHistory ownerHistory,
                     IfcLabel name, IfcText description, IfcLabel objectType,
                     IfcRelAssignsToGroup isGroupedBy,
                     IfcRelServicesBuildings[] servicesBuildings) {

        super(globalId, ownerHistory, name, description, objectType,
                isGroupedBy);
        ServicesBuildings = servicesBuildings;
    }
}
