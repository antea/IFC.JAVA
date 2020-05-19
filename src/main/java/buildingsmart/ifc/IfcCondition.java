package buildingsmart.ifc;

public class IfcCondition extends IfcGroup {
    public IfcCondition(IfcGloballyUniqueId globalId,
                        IfcOwnerHistory ownerHistory, IfcLabel name,
                        IfcText description, IfcLabel objectType,
                        IfcRelAssignsToGroup isGroupedBy) {
        super(globalId, ownerHistory, name, description, objectType,
                isGroupedBy);
    }
}
