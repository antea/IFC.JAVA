package buildingsmart.ifc;

public class IfcEdgeFeature extends IfcFeatureElementSubtraction {
    private final IfcLengthMeasure FeatureLength;

    public IfcEdgeFeature(IfcGloballyUniqueId globalId,
                          IfcOwnerHistory ownerHistory, IfcLabel name,
                          IfcText description, IfcLabel objectType,
                          IfcRelVoidsElement voidsElements,
                          IfcLengthMeasure featureLength) {

        super(globalId, ownerHistory, name, description, objectType,
                voidsElements);
        FeatureLength = featureLength;
    }
}
