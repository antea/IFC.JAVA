package buildingsmart.ifc;

public class IfcControl extends IfcObject {
    private IfcRelAssignsToControl[] Controls;

    /**
     * Creates a new IfcObjectDefinition, using the provided globalId.
     *
     * @param globalId     Assignment of a globally unique identifier within the
     *                     entire software world.
     * @param ownerHistory Assignment of the information about the current
     *                     ownership of that object, including owning actor,
     *                     application, local identification and information
     *                     captured about the recent changes of the object,
     *                     NOTE: only the last modification in stored.
     * @param name         Optional name for use by the participating software
     *                     systems or users. For some subtypes of IfcRoot the
     *                     insertion of the Name attribute may be required. This
     *                     would be enforced by a where rule.
     * @param description  Optional description, provided for exchanging
     *                     informative comments.
     * @param objectType   The type denotes a particular type that indicates the
     *                     object further. The use has to be established at the
     *                     level of instantiable subtypes. In particular it
     *                     holds the user defined type, if the enumeration of
     *                     the attribute PredefinedType is set to USERDEFINED.
     * @throws IllegalArgumentException If globalId or ownerHistory are null, or
     *                                  if globalId was used in another instance
     *                                  of this class.
     */
    public IfcControl(IfcGloballyUniqueId globalId,
                      IfcOwnerHistory ownerHistory, IfcLabel name,
                      IfcText description, IfcLabel objectType) {
        super(globalId, ownerHistory, name, description, objectType);
    }

    /**
     * Creates a new IfcObjectDefinition and generates a pseudo random
     * globalId.
     *
     * @param ownerHistory Assignment of the information about the current
     *                     ownership of that object, including owning actor,
     *                     application, local identification and information
     *                     captured about the recent changes of the object,
     *                     NOTE: only the last modification in stored.
     * @param name         Optional name for use by the participating software
     *                     systems or users. For some subtypes of IfcRoot the
     *                     insertion of the Name attribute may be required. This
     *                     would be enforced by a where rule.
     * @param description  Optional description, provided for exchanging
     *                     informative comments.
     * @param objectType   The type denotes a particular type that indicates the
     *                     object further. The use has to be established at the
     *                     level of instantiable subtypes. In particular it
     *                     holds the user defined type, if the enumeration of
     *                     the attribute PredefinedType is set to USERDEFINED.
     * @throws IllegalArgumentException If ownerHistory is null.
     */
    public IfcControl(IfcOwnerHistory ownerHistory, IfcLabel name,
                      IfcText description, IfcLabel objectType) {
        super(ownerHistory, name, description, objectType);
    }
}
