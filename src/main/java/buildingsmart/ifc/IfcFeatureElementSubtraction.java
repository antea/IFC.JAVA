package buildingsmart.ifc;

public class IfcFeatureElementSubtraction extends IfcFeatureElement {
    private final IfcRelVoidsElement VoidsElements;

    public IfcFeatureElementSubtraction(IfcGloballyUniqueId globalId,
                                        IfcOwnerHistory ownerHistory,
                                        IfcLabel name, IfcText description,
                                        IfcLabel objectType,
                                        IfcRelVoidsElement voidsElements) {

        super(globalId, ownerHistory, name, description, objectType);
        VoidsElements = voidsElements;
    }
}
