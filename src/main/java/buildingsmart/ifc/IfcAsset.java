package buildingsmart.ifc;

public class IfcAsset extends IfcGroup {
    private final String AssetID;
    private IfcCostValue OriginalValue;
    private IfcCostValue CurrentValue;
    private IfcCostValue TotalReplacementCost;
    private IfcActorSelect Owner;
    private IfcActorSelect User;
    private IfcPerson ResponsiblePerson;
    private IfcCalendarDate IncorporationDate;
    private IfcCostValue DepreciatedValue;

    public IfcAsset(IfcGloballyUniqueId globalId, IfcOwnerHistory ownerHistory,
                    IfcLabel name, IfcText description, IfcLabel objectType,
                    IfcRelAssignsToGroup isGroupedBy, String assetID) {

        super(globalId, ownerHistory, name, description, objectType,
                isGroupedBy);
        AssetID = assetID;
    }
}
