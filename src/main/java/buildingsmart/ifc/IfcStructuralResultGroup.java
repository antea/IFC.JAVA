package buildingsmart.ifc;

public class IfcStructuralResultGroup extends IfcGroup {
    private final IfcAnalysisTheoryTypeEnum TheoryType;
    private IfcStructuralLoadGroup ResultForLoadGroup;
    private boolean IsLinear;
    private IfcStructuralAnalysisModel[] ResultGroupFor;

    public IfcStructuralResultGroup(IfcGloballyUniqueId globalId,
                                    IfcOwnerHistory ownerHistory, IfcLabel name,
                                    IfcText description, IfcLabel objectType,
                                    IfcRelAssignsToGroup isGroupedBy,
                                    IfcAnalysisTheoryTypeEnum theoryType) {
        super(globalId, ownerHistory, name, description, objectType,
                isGroupedBy);
        TheoryType = theoryType;
    }
}
