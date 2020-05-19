package buildingsmart.ifc;

public class IfcWorkPlan extends IfcWorkControl {
    public IfcWorkPlan(IfcGloballyUniqueId globalId,
                       IfcOwnerHistory ownerHistory, IfcLabel name,
                       IfcText description, IfcLabel objectType,
                       String identifier) {
        super(globalId, ownerHistory, name, description, objectType,
                identifier);
    }
}
