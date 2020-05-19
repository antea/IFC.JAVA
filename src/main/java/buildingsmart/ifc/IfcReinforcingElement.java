package buildingsmart.ifc;

public class IfcReinforcingElement extends IfcBuildingElementComponent {
    private final String SteelGrade;

    public IfcReinforcingElement(IfcGloballyUniqueId globalId,
                                 IfcOwnerHistory ownerHistory, IfcLabel name,
                                 IfcText description, IfcLabel objectType,
                                 String steelGrade) {
        super(globalId, ownerHistory, name, description, objectType);
        SteelGrade = steelGrade;
    }
}
