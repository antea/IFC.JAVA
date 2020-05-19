package buildingsmart.ifc;

public class IfcRoundedEdgeFeature extends IfcEdgeFeature {
    private final IfcLengthMeasure Radius;

    public IfcRoundedEdgeFeature(IfcGloballyUniqueId globalId,
                                 IfcOwnerHistory ownerHistory, IfcLabel name,
                                 IfcText description, IfcLabel objectType,
                                 IfcRelVoidsElement voidsElements,
                                 IfcLengthMeasure featureLength,
                                 IfcLengthMeasure radius) {

        super(globalId, ownerHistory, name, description, objectType,
                voidsElements, featureLength);
        Radius = radius;
    }
}
