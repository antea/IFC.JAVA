package buildingsmart.ifc;

import com.sun.istack.internal.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * An <i>IfcObjectDefinition</i> is the generalization of any semantically
 * treated thing or process, either being a type or an occurrences. Object
 * defintions can be named, using the inherited <i>Name</i> attribute, which
 * should be a user recognizable label for the object occurrence. Further
 * explanations to the object can be given using the inherited
 * <i>Description</i> attribute.&nbsp;</p>
 * <p>Objects are independent pieces
 * of information that might contain or reference other pieces of information.
 * There are three essential kinds of relationships in which object definitions
 * (by their instantiable subtypes) can be involved:</p>
 * <ul>
 *   <li><b>Assignment
 * of other objects</b> - an assignment
 * relationship (<i>IfcRelAssigns</i>) that
 * refers to other types of objects and creates a bi-directional
 * association. The semantic of the assignment is established at the level
 * of the subtypes of the general <i>IfcRelAssigns</i>
 * relationship. There is no dependency implied a priori by the assignment.</li>
 *   <li><b>Association
 * to external resources</b> - an
 * association relationship (<i>IfcRelAssociates</i>)
 * that refers to external sources of information (most notably a
 * classification
 * or document) and creates a uni-directional association. There is no
 * dependency implied by the association.</li>
 *   <li><b>Aggregation
 * of other objects</b> - an
 * aggregation relationship (<i>IfcRelDecomposes</i>)
 * that establishes a whole/part relation and creates a bi-directional
 * relation. There is an implied dependency established.</li>
 * </ul>
 */
public class IfcObjectDefinition extends IfcRoot {
    //private Set<IfcRelAssigns> hasAssignments;
    private Set<IfcRelDecomposes> isDecomposedBy;
    private IfcRelDecomposes decomposes;
    //private Set<IfcRelAssociates> hasAssociations;

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
     * @throws IllegalArgumentException If globalId or ownerHistory are null, or
     *                                  if globalId was used in another instance
     *                                  of this class.
     */
    public IfcObjectDefinition(@NotNull IfcGloballyUniqueId globalId,
                               @NotNull IfcOwnerHistory ownerHistory,
                               IfcLabel name, IfcText description) {
        super(globalId, ownerHistory, name, description);
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
     * @throws IllegalArgumentException If ownerHistory is null.
     */
    public IfcObjectDefinition(@NotNull IfcOwnerHistory ownerHistory,
                               IfcLabel name, IfcText description) {
        super(ownerHistory, name, description);
    }

    /**
     * @return A copy of isDecomposedBy. Operations performed on this Set don't
     * have any effect on isDecomposedBy. This is done to prevent adding illegal
     * IfcRelDecomposes to the Set.
     * @see #setIsDecomposedBy(Set)
     */
    public Set<IfcRelDecomposes> getIsDecomposedBy() {
        return new HashSet<>(isDecomposedBy);
    }

    /**
     * @param isDecomposedBy Reference to the decomposition relationship, that
     *                       allows this object to be the composition of other
     *                       objects. An object can be decomposed by several
     *                       other objects.
     * @throws IllegalArgumentException If this object is not the relatingObject
     *                                  in all relationships contained in the
     *                                  Set.
     */
    public void setIsDecomposedBy(Set<IfcRelDecomposes> isDecomposedBy) {
        for (IfcRelDecomposes rel : isDecomposedBy) {
            if (!rel.getRelatingObject().equals(this)) {
                throw new IllegalArgumentException(
                        "any IfcRelDecomposes part of isDecomposedBy must " +
                                "have this object as its relatingObject");
            }
        }
        this.isDecomposedBy = isDecomposedBy;
    }

    public IfcRelDecomposes getDecomposes() {
        return decomposes;
    }

    /**
     * @param decomposes References to the decomposition relationship, that
     *                   allows this object to be a part of the decomposition.
     *                   An object can only be part of a single decomposition
     *                   (to allow hierarchical strutures only).
     * @throws IllegalArgumentException If decomposes.relatedObjects does not
     *                                  contain this object.
     */
    public void setDecomposes(IfcRelDecomposes decomposes) {
        if (!decomposes.getRelatedObjects().contains(this)) {
            throw new IllegalArgumentException(
                    "decomposes.relatedObjects must contain this object");
        }
        this.decomposes = decomposes;
    }
}
