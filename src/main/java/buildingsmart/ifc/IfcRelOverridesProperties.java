package buildingsmart.ifc;

public class IfcRelOverridesProperties extends IfcRelDefinesByProperties {
    private final IfcProperty[] OverridingProperties;

    public IfcRelOverridesProperties(IfcGloballyUniqueId globalId,
                                     IfcOwnerHistory ownerHistory,
                                     IfcLabel name, IfcText description,
                                     IfcObject[] relatedObjects,
                                     IfcPropertySetDefinition relatingPropertyDefinition,
                                     IfcProperty[] overridingProperties) {

        super(globalId, ownerHistory, name, description, relatedObjects,
                relatingPropertyDefinition);
        OverridingProperties = overridingProperties;
    }
}
