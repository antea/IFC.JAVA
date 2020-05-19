package buildingsmart.ifc;

public class IfcRelAssociatesConstraint extends IfcRelAssociates {
    private final String Intent;
    private IfcConstraint RelatingConstraint;

    public IfcRelAssociatesConstraint(IfcGloballyUniqueId globalId,
                                      IfcOwnerHistory ownerHistory,
                                      IfcLabel name, IfcText description,
                                      IfcRoot[] relatedObjects, String intent) {

        super(globalId, ownerHistory, name, description, relatedObjects);
        Intent = intent;
    }
}
