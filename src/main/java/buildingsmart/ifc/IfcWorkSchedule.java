package buildingsmart.ifc;

public class IfcWorkSchedule extends IfcWorkControl {
    public IfcWorkSchedule(IfcGloballyUniqueId globalId,
                           IfcOwnerHistory ownerHistory, IfcLabel name,
                           IfcText description, IfcLabel objectType,
                           String identifier) {
        super(globalId, ownerHistory, name, description, objectType,
                identifier);
    }
}
