package buildingsmart.ifc;

public class IfcStructuralAnalysisModel extends IfcSystem {
    private final IfcAnalysisModelTypeEnum PredefinedType;
    private IfcAxis2Placement3D OrientationOf2DPlane;
    private IfcStructuralLoadGroup[] LoadedBy;
    private IfcStructuralResultGroup[] HasResults;

    public IfcStructuralAnalysisModel(IfcGloballyUniqueId globalId,
                                      IfcOwnerHistory ownerHistory,
                                      IfcLabel name, IfcText description,
                                      IfcLabel objectType,
                                      IfcRelAssignsToGroup isGroupedBy,
                                      IfcRelServicesBuildings[] servicesBuildings,
                                      IfcAnalysisModelTypeEnum predefinedType) {

        super(globalId, ownerHistory, name, description, objectType,
                isGroupedBy, servicesBuildings);
        PredefinedType = predefinedType;
    }
}
