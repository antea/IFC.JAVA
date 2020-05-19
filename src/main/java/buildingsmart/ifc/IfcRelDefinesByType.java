package buildingsmart.ifc;

public class IfcRelDefinesByType extends IfcRelDefines {
    private final IfcTypeObject RelatingType;

    public IfcRelDefinesByType(IfcGloballyUniqueId globalId,
                               IfcOwnerHistory ownerHistory, IfcLabel name,
                               IfcText description, IfcObject[] relatedObjects,
                               IfcTypeObject relatingType) {

        super(globalId, ownerHistory, name, description, relatedObjects);
        RelatingType = relatingType;
    }
}
