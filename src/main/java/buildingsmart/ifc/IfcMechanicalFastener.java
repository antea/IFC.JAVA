package buildingsmart.ifc;

public class IfcMechanicalFastener extends IfcFastener {
    private final IfcLengthMeasure NominalDiameter;
    private IfcLengthMeasure NominalLength;

    public IfcMechanicalFastener(IfcGloballyUniqueId globalId,
                                 IfcOwnerHistory ownerHistory, IfcLabel name,
                                 IfcText description, IfcLabel objectType,
                                 IfcLengthMeasure nominalDiameter) {

        super(globalId, ownerHistory, name, description, objectType);
        NominalDiameter = nominalDiameter;
    }
}
