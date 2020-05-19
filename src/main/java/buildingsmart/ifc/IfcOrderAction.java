package buildingsmart.ifc;

public class IfcOrderAction extends IfcTask {
    private final String ActionID;

    public IfcOrderAction(IfcGloballyUniqueId globalId,
                          IfcOwnerHistory ownerHistory, IfcLabel name,
                          IfcText description, IfcLabel objectType,
                          IfcRelAssignsToProcess[] operatesOn, String taskId,
                          String actionID) {

        super(globalId, ownerHistory, name, description, objectType, operatesOn,
                taskId);
        ActionID = actionID;
    }
}
