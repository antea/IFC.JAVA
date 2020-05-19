package buildingsmart.ifc;

public class IfcRelAssociatesAppliedValue extends IfcRelAssociates {
    private final IfcAppliedValue RelatingAppliedValue;

    public IfcRelAssociatesAppliedValue(IfcGloballyUniqueId globalId,
                                        IfcOwnerHistory ownerHistory,
                                        IfcLabel name, IfcText description,
                                        IfcRoot[] relatedObjects,
                                        IfcAppliedValue relatingAppliedValue) {

        super(globalId, ownerHistory, name, description, relatedObjects);
        RelatingAppliedValue = relatingAppliedValue;
    }
}
