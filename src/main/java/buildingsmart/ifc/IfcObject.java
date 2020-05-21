package buildingsmart.ifc;

import com.sun.istack.internal.NotNull;

/**
 * An <i>IfcObject</i> is the generalization of any semantically treated thing
 * or process. Objects are things as they appear - i.e. occurrences. </p>
 * <blockquote><font size="-1"> NOTE Examples of <i>IfcObject</i>
 * include physically tangible items, such as wall, beam or covering, physically
 * existing items, such as spaces, or conceptual items, such as grids or virtual
 * boundaries. It also stands for processes, such as work tasks, for controls,
 * such as cost items, for actors, such as persons involved in the design
 * process, etc. </font></blockquote>
 * <p>Objects can be named, using the inherited <i>Name</i>
 * attribute, which should be a user recognizable label for the object
 * occurrence. Further explanations to the object can be given using the
 * inherited <i>Description</i> attribute. The <i>ObjectType</i> attribute is
 * used:</p>
 * <ul>
 *   <li>to store the user defined value for all subtypes of <i>IfcObject</i>,
 * where a <i>PredefinedType</i> attribute is given, and its
 * value is set to USERDEFINED.</li>
 *   <li>to provide a type information (could be seen as a very
 * lightweight classifier) of the subtype of <i>IfcObject</i>,
 * if no <i>PredefinedType</i> attribute is given. This is
 * often the case, if no comprehensive list of predefined types is
 * available.</li>
 * </ul>
 * <p>Objects are independent pieces of information that might
 * contain or reference other pieces of information. There are four
 * essential kind of relationships in which objects can be involved:</p>
 * <ul>
 *   <li><b>Assignment of other objects</b> - an
 * assignment relationship that refers to other types of objects. See
 * supertype <i>IfcObjectDefinition</i> for more information.</li>
 *   <li><b>Association to external resources</b> - an
 * association relationship that refers to external sources of
 * information.&nbsp;See supertype <i>IfcObjectDefinition</i>
 * for more information.</li>
 *   <li><b>Aggregation of other objects</b> - an
 * aggregation relationship that establishes a whole/part relation. See
 * supertype <i>IfcObjectDefinition</i> for more information.<br>
 *     <br>
 *   </li>
 *   <li><b>Refinement by type and properties</b> - a
 * refinement relationship (<i>IfcRelDefines</i>) that uses a
 * type definition or (partial) property set definition to define the
 * properties of the object instance. It is a specific - occurrence
 * relationship with implied dependencies (as the occurrence properties
 * depend on the specific properties).</li>
 * </ul>
 */
public abstract class IfcObject extends IfcObjectDefinition {
    private final IfcLabel objectType;
    //private IfcRelDefines[] IsDefinedBy;

    /**
     * Creates a new IfcObject, using the provided globalId.
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
    public IfcObject(@NotNull IfcGloballyUniqueId globalId,
                     @NotNull IfcOwnerHistory ownerHistory, IfcLabel name,
                     IfcText description, IfcLabel objectType) {
        super(globalId, ownerHistory, name, description);
        this.objectType = objectType;
    }

    /**
     * Creates a new IfcObject and generates a pseudo random globalId.
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
    public IfcObject(@NotNull IfcOwnerHistory ownerHistory, IfcLabel name,
                     IfcText description, IfcLabel objectType) {
        this(new IfcGloballyUniqueId(), ownerHistory, name, description,
                objectType);
    }
}
