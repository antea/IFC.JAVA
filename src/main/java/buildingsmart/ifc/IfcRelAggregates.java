package buildingsmart.ifc;

import com.sun.istack.internal.NotNull;

import java.util.Set;

/**
 * The aggregation relationship
 * <I>IfcRelAggregates</I> is a special type of the general
 * composition/decomposition (or whole/part) relationship
 * <I>IfcRelDecomposes</I>.
 * The aggregation relationship can be applied to all subtypes of object.</P>
 * <P> Some further specializations of decomposition may imply additional
 * constraints and meanings, such as the requirement of aggregates to represent
 * physical containment. In cases of physical containment the representation
 * (within the same representation context) of the whole can be taken from the
 * sum of the representations of the parts.</P>
 * <BLOCKQUOTE><FONT SIZE="-1">EXAMPLE: A roof is the aggregation of the
 * roof elements, such as roof slabs, rafters, purlins, etc. Within the same
 * representation context, e.g. the detailed geometric representation, the shape
 * representation of the roof is given by the shape representation of its
 * parts</FONT></BLOCKQUOTE>
 * <P> Decompositions imply a dependency, i.e. the definition of the
 * whole depends on the definition of the parts and the parts depend on the
 * existence of the whole. The behavior that is implied from the dependency has
 * to be established inside the applications.
 */
public class IfcRelAggregates extends IfcRelDecomposes {
    /**
     * Creates a new IfcRelAggregates, using the provided globalId.
     *
     * @param globalId       Assignment of a globally unique identifier within
     *                       the entire software world.
     * @param ownerHistory   Assignment of the information about the current
     *                       ownership of that object, including owning actor,
     *                       application, local identification and information
     *                       captured about the recent changes of the object,
     *                       NOTE: only the last modification in stored.
     * @param name           Optional name for use by the participating software
     *                       systems or users. For some subtypes of IfcRoot the
     *                       insertion of the Name attribute may be required.
     *                       This would be enforced by a where rule.
     * @param description    Optional description, provided for exchanging
     *                       informative comments.
     * @param relatingObject The object that represents the nest or
     *                       aggregation.
     * @param relatedObjects The objects being nested or aggregated.
     * @throws IllegalArgumentException If globalId, ownerHistory,
     * relatingObject
     *                                  or relatedObjects are null, if globalId
     *                                  was used in another instance of this
     *                                  class or its superclass, if the size of
     *                                  relatedObjects is zero, if
     *                                  relatedObjects
     *                                  contains relatingObject.
     */
    public IfcRelAggregates(@NotNull IfcGloballyUniqueId globalId,
                            @NotNull IfcOwnerHistory ownerHistory,
                            IfcLabel name, IfcText description,
                            @NotNull IfcObjectDefinition relatingObject,
                            @NotNull Set<IfcObjectDefinition> relatedObjects) {
        super(globalId, ownerHistory, name, description, relatingObject,
                relatedObjects);
    }

    /**
     * Creates a new IfcRelAggregates and generates a pseudo random globalId.
     *
     * @param ownerHistory   Assignment of the information about the current
     *                       ownership of that object, including owning actor,
     *                       application, local identification and information
     *                       captured about the recent changes of the object,
     *                       NOTE: only the last modification in stored.
     * @param name           Optional name for use by the participating software
     *                       systems or users. For some subtypes of IfcRoot the
     *                       insertion of the Name attribute may be required.
     *                       This would be enforced by a where rule.
     * @param description    Optional description, provided for exchanging
     *                       informative comments.
     * @param relatingObject The object that represents the nest or
     *                       aggregation.
     * @param relatedObjects The objects being nested or aggregated.
     * @throws IllegalArgumentException If ownerHistory, relatingObject or
     *                                  relatedObjects are null, if the size of
     *                                  relatedObjects is zero, if
     *                                  relatedObjects
     *                                  contains relatingObject.
     */
    public IfcRelAggregates(@NotNull IfcOwnerHistory ownerHistory,
                            IfcLabel name, IfcText description,
                            @NotNull IfcObjectDefinition relatingObject,
                            @NotNull Set<IfcObjectDefinition> relatedObjects) {
        super(ownerHistory, name, description, relatingObject, relatedObjects);
    }

    /**
     * Creates a new IfcRelAggregates, using the provided globalId.
     *
     * @param globalId       Assignment of a globally unique identifier within
     *                       the entire software world.
     * @param ownerHistory   Assignment of the information about the current
     *                       ownership of that object, including owning actor,
     *                       application, local identification and information
     *                       captured about the recent changes of the object,
     *                       NOTE: only the last modification in stored.
     * @param name           Optional name for use by the participating software
     *                       systems or users. For some subtypes of IfcRoot the
     *                       insertion of the Name attribute may be required.
     *                       This would be enforced by a where rule.
     * @param description    Optional description, provided for exchanging
     *                       informative comments.
     * @param relatingObject The object that represents the nest or
     *                       aggregation.
     * @param relatedObjects The objects being nested or aggregated.
     * @throws IllegalArgumentException If globalId, ownerHistory,
     * relatingObject
     *                                  or relatedObjects are null, if globalId
     *                                  was used in another instance of this
     *                                  class or its superclass, if the size of
     *                                  relatedObjects is zero, if
     *                                  relatedObjects
     *                                  contains relatingObject.
     */
    public IfcRelAggregates(@NotNull IfcGloballyUniqueId globalId,
                            @NotNull IfcOwnerHistory ownerHistory,
                            IfcLabel name, IfcText description,
                            @NotNull IfcObjectDefinition relatingObject,
                            @NotNull IfcObjectDefinition... relatedObjects) {
        super(globalId, ownerHistory, name, description, relatingObject,
                relatedObjects);
    }

    /**
     * Creates a new IfcRelAggregates and generates a pseudo random globalId.
     *
     * @param ownerHistory   Assignment of the information about the current
     *                       ownership of that object, including owning actor,
     *                       application, local identification and information
     *                       captured about the recent changes of the object,
     *                       NOTE: only the last modification in stored.
     * @param name           Optional name for use by the participating software
     *                       systems or users. For some subtypes of IfcRoot the
     *                       insertion of the Name attribute may be required.
     *                       This would be enforced by a where rule.
     * @param description    Optional description, provided for exchanging
     *                       informative comments.
     * @param relatingObject The object that represents the nest or
     *                       aggregation.
     * @param relatedObjects The objects being nested or aggregated.
     * @throws IllegalArgumentException If ownerHistory, relatingObject or
     *                                  relatedObjects are null, if the size of
     *                                  relatedObjects is zero, if
     *                                  relatedObjects
     *                                  contains relatingObject.
     */
    public IfcRelAggregates(@NotNull IfcOwnerHistory ownerHistory,
                            IfcLabel name, IfcText description,
                            @NotNull IfcObjectDefinition relatingObject,
                            @NotNull IfcObjectDefinition... relatedObjects) {
        super(ownerHistory, name, description, relatingObject, relatedObjects);
    }
}
