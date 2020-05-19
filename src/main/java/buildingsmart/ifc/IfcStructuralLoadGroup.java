package buildingsmart.ifc;

public class IfcStructuralLoadGroup extends IfcGroup {
    private final IfcLoadGroupTypeEnum PredefinedType;
    private IfcActionTypeEnum ActionType;
    private IfcActionSourceTypeEnum ActionSource;
    private double Coefficient;
    private String Purpose;
    private IfcStructuralResultGroup[] SourceOfResultGroup;
    private IfcStructuralAnalysisModel[] LoadGroupFor;

    public IfcStructuralLoadGroup(IfcGloballyUniqueId globalId,
                                  IfcOwnerHistory ownerHistory, IfcLabel name,
                                  IfcText description, IfcLabel objectType,
                                  IfcRelAssignsToGroup isGroupedBy,
                                  IfcLoadGroupTypeEnum predefinedType) {

        super(globalId, ownerHistory, name, description, objectType,
                isGroupedBy);
        PredefinedType = predefinedType;
    }
}
