package buildingsmart.ifc;

public class IfcOpeningElement extends IfcFeatureElementSubtraction {
    private final IfcRelFillsElement[] HasFillings;

    public IfcOpeningElement(IfcGloballyUniqueId globalId,
                             IfcOwnerHistory ownerHistory, IfcLabel name,
                             IfcText description, IfcLabel objectType,
                             IfcRelVoidsElement voidsElements,
                             IfcRelFillsElement[] hasFillings) {

        super(globalId, ownerHistory, name, description, objectType,
                voidsElements);
        HasFillings = hasFillings;
    }
}
