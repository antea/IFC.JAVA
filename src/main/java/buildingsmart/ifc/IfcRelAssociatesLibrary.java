package buildingsmart.ifc;

public class IfcRelAssociatesLibrary extends IfcRelAssociates {
    private final IfcLibrarySelect RelatingLibrary;

    public IfcRelAssociatesLibrary(IfcGloballyUniqueId globalId,
                                   IfcOwnerHistory ownerHistory, IfcLabel name,
                                   IfcText description,
                                   IfcRoot[] relatedObjects,
                                   IfcLibrarySelect relatingLibrary) {

        super(globalId, ownerHistory, name, description, relatedObjects);
        RelatingLibrary = relatingLibrary;
    }
}
