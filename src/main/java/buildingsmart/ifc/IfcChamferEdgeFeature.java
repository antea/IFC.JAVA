package buildingsmart.ifc;

public class IfcChamferEdgeFeature extends IfcEdgeFeature {
    private final IfcLengthMeasure Width;
    private IfcLengthMeasure Height;

    public IfcChamferEdgeFeature(IfcGloballyUniqueId globalId,
                                 IfcOwnerHistory ownerHistory, IfcLabel name,
                                 IfcText description, IfcLabel objectType,
                                 IfcRelVoidsElement voidsElements,
                                 IfcLengthMeasure featureLength,
                                 IfcLengthMeasure width) {
        super(globalId, ownerHistory, name, description, objectType,
                voidsElements, featureLength);
        Width = width;
    }
}
