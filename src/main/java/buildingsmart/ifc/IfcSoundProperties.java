package buildingsmart.ifc;

public class IfcSoundProperties extends IfcPropertySetDefinition {
    private final boolean IsAttenuating;
    private IfcSoundScaleEnum SoundScale;
    private IfcSoundValue[] SoundValues;

    public IfcSoundProperties(IfcGloballyUniqueId globalId,
                              IfcOwnerHistory ownerHistory, IfcLabel name,
                              IfcText description,
                              IfcRelAssociates[] hasAssociations,
                              IfcRelDefinesByProperties[] propertyDefinitionOf,
                              boolean isAttenuating) {

        super(globalId, ownerHistory, name, description, hasAssociations,
                propertyDefinitionOf);
        IsAttenuating = isAttenuating;
    }
}
