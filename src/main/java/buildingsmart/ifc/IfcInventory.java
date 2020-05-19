package buildingsmart.ifc;

public class IfcInventory extends IfcGroup {
    private final IfcInventoryTypeEnum InventoryType;
    private IfcActorSelect Jurisdiction;
    private IfcPerson[] ResponsiblePersons;
    private IfcCalendarDate LastUpdateDate;
    private IfcCostValue CurrentValue;
    private IfcCostValue OriginalValue;

    public IfcInventory(IfcGloballyUniqueId globalId,
                        IfcOwnerHistory ownerHistory, IfcLabel name,
                        IfcText description, IfcLabel objectType,
                        IfcRelAssignsToGroup isGroupedBy,
                        IfcInventoryTypeEnum inventoryType) {

        super(globalId, ownerHistory, name, description, objectType,
                isGroupedBy);
        InventoryType = inventoryType;
    }
}
