package buildingsmart.ifc;

public class IfcRelAssociatesClassification extends IfcRelAssociates {
    private final IfcClassificationNotationSelect RelatingClassification;

    public IfcRelAssociatesClassification(IfcGloballyUniqueId globalId,
                                          IfcOwnerHistory ownerHistory,
                                          IfcLabel name, IfcText description,
                                          IfcRoot[] relatedObjects,
                                          IfcClassificationNotationSelect relatingClassification) {

        super(globalId, ownerHistory, name, description, relatedObjects);
        RelatingClassification = relatingClassification;
    }
}
