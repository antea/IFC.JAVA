package buildingsmart.ifc;

public class IfcTask extends IfcProcess {
    private final String TaskId;
    private String Status;
    private String WorkMethod;
    private boolean IsMilestone;
    private int Priority;

    public IfcTask(IfcGloballyUniqueId globalId, IfcOwnerHistory ownerHistory,
                   IfcLabel name, IfcText description, IfcLabel objectType,
                   IfcRelAssignsToProcess[] operatesOn, String taskId) {

        super(globalId, ownerHistory, name, description, objectType,
                operatesOn);
        TaskId = taskId;
    }
}
