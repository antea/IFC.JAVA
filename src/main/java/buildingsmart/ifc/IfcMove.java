package buildingsmart.ifc;

public class IfcMove extends IfcTask {
    private final IfcSpatialStructureElement MoveFrom;
    private IfcSpatialStructureElement MoveTo;
    private String[] PunchList;

    public IfcMove(IfcGloballyUniqueId globalId, IfcOwnerHistory ownerHistory,
                   IfcLabel name, IfcText description, IfcLabel objectType,
                   IfcRelAssignsToProcess[] operatesOn, String taskId,
                   IfcSpatialStructureElement moveFrom) {

        super(globalId, ownerHistory, name, description, objectType, operatesOn,
                taskId);
        MoveFrom = moveFrom;
    }
}
