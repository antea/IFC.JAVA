package buildingsmart.ifc;

public class IfcOccupant extends IfcActor {
    private final IfcOccupantTypeEnum PredefinedType;

    public IfcOccupant(IfcGloballyUniqueId globalId,
                       IfcOwnerHistory ownerHistory, IfcLabel name,
                       IfcText description, IfcLabel objectType,
                       IfcActorSelect theActor,
                       IfcOccupantTypeEnum predefinedType) {

        super(globalId, ownerHistory, name, description, objectType, theActor);
        PredefinedType = predefinedType;
    }
}
