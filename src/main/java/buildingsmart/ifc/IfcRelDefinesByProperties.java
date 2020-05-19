package buildingsmart.ifc;

public class IfcRelDefinesByProperties extends IfcRelDefines {
    private final IfcPropertySetDefinition RelatingPropertyDefinition;

    public IfcRelDefinesByProperties(IfcGloballyUniqueId globalId,
                                     IfcOwnerHistory ownerHistory,
                                     IfcLabel name, IfcText description,
                                     IfcObject[] relatedObjects,
                                     IfcPropertySetDefinition relatingPropertyDefinition) {

        super(globalId, ownerHistory, name, description, relatedObjects);
        RelatingPropertyDefinition = relatingPropertyDefinition;
    }
}
