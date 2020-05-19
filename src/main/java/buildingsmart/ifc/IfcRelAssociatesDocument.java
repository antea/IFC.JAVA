package buildingsmart.ifc;

public class IfcRelAssociatesDocument extends IfcRelAssociates {
    private final IfcDocumentSelect RelatingDocument;

    public IfcRelAssociatesDocument(IfcGloballyUniqueId globalId,
                                    IfcOwnerHistory ownerHistory, IfcLabel name,
                                    IfcText description,
                                    IfcRoot[] relatedObjects,
                                    IfcDocumentSelect relatingDocument) {

        super(globalId, ownerHistory, name, description, relatedObjects);
        RelatingDocument = relatingDocument;
    }
}
