package buildingsmart.ifc;

public class IfcRelAssociatesMaterial extends IfcRelAssociates {
    private final IfcMaterialSelect RelatingMaterial;

    public IfcRelAssociatesMaterial(IfcGloballyUniqueId globalId,
                                    IfcOwnerHistory ownerHistory, IfcLabel name,
                                    IfcText description,
                                    IfcRoot[] relatedObjects,
                                    IfcMaterialSelect relatingMaterial) {
        super(globalId, ownerHistory, name, description, relatedObjects);
        RelatingMaterial = relatingMaterial;
    }
}
