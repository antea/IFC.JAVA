package buildingsmart.ifc;

public class IfcTendonAnchor extends IfcReinforcingElement {
    public IfcTendonAnchor(IfcGloballyUniqueId globalId,
                           IfcOwnerHistory ownerHistory, IfcLabel name,
                           IfcText description, IfcLabel objectType,
                           String steelGrade) {
        super(globalId, ownerHistory, name, description, objectType,
                steelGrade);
    }
}
