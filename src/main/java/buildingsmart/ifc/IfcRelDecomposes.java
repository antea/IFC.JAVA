/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 *
 * This file is part of IFC.JAVA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package buildingsmart.ifc;

import buildingsmart.io.Attribute;
import buildingsmart.io.Order;
import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The decomposition relationship,
 * <I>IfcRelDecomposes</I>, defines the general concept of
 * elements being composed or decomposed. The decomposition relationship denotes
 * a whole/part hierarchy with the ability to navigate from the whole (the
 * composition) to the parts and vice versa.</P>
 * <P>Decompositions may be constraint by requiring both, the whole and
 * its parts, to be of the same type - thus establishing a nesting relationship.
 * Or they may require some form of physical containment, thus establishing
 * special types of aggregation relationships.</P>
 * <BLOCKQUOTE><FONT SIZE="-1"> NOTE: There are two special names for
 * decomposition, which are linguistically distinguished, nesting and
 * aggregation. The subtypes of <I>IfcRelDecomposes</I> will introduce either
 * the nesting or aggregation convention (see <I>IfcRelNests</I> and
 * <I>IfcRelAggregates</I>).</FONT></BLOCKQUOTE>
 * <BLOCKQUOTE><FONT SIZE="-1"> EXAMPLE: A cost element is a nest of
 * other cost elements. Or a structural frame is an aggregation of beams and
 * columns. Both are applications of decomposition relationship
 * .</FONT></BLOCKQUOTE>
 * <P>Decompositions imply a dependency, i.e. the definition of the whole
 * depends on the definition of the parts and the parts depend on the existence
 * of the whole. The decomposition relationship can be applied in a recursive
 * manner, i.e. a decomposed element can be part in another decomposition .
 * Cyclic references have to be prevented at application level.</P>
 */
public class IfcRelDecomposes extends IfcRelationship {
    @Attribute
    @Order(value = 4)
    private final IfcObjectDefinition relatingObject;
    @Attribute
    @Order(value = 5)
    private final Set<IfcObjectDefinition> relatedObjects;

    //TODO: test constructor

    /**
     * Creates a new IfcRelDecomposes, using the provided globalId, and
     * automatically modifies relatingObject and relatedObjects to make them all
     * contain a reference to this relationship, either in isDecomposedBy or
     * decomposes.
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
    public IfcRelDecomposes(@NotNull IfcGloballyUniqueId globalId,
                            @NotNull IfcOwnerHistory ownerHistory,
                            IfcLabel name, IfcText description,
                            @NotNull IfcObjectDefinition relatingObject,
                            @NotNull Set<IfcObjectDefinition> relatedObjects) {
        super(globalId, ownerHistory, name, description);
        if (relatingObject == null) {
            throw new IllegalArgumentException("relatingObject cannot be null");
        }
        if (relatedObjects == null) {
            throw new IllegalArgumentException("relatedObjects cannot be null");
        }
        if (relatedObjects.size() == 1) {
            throw new IllegalArgumentException(
                    "size of relatedObjects must be at least one");
        }
        if (relatedObjects.contains(relatingObject)) {
            throw new IllegalArgumentException(
                    "relatedObjects cannot contain relatingObject");
        }
        this.relatingObject = relatingObject;
        this.relatedObjects = relatedObjects;

        relatingObject.addToIsDecomposedBy(this);
        for (IfcObjectDefinition obj : relatedObjects) {
            obj.setDecomposes(this);
        }
    }

    /**
     * Creates a new IfcRelDecomposes and generates a pseudo random globalId,
     * and automatically modifies relatingObject and relatedObjects to make them
     * all contain a reference to this relationship, either in isDecomposedBy or
     * decomposes.
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
    public IfcRelDecomposes(@NotNull IfcOwnerHistory ownerHistory,
                            IfcLabel name, IfcText description,
                            @NotNull IfcObjectDefinition relatingObject,
                            @NotNull Set<IfcObjectDefinition> relatedObjects) {
        this(new IfcGloballyUniqueId(), ownerHistory, name, description,
                relatingObject, relatedObjects);
    }

    /**
     * Creates a new IfcRelDecomposes, using the provided globalId, and
     * automatically modifies relatingObject and relatedObjects to make them all
     * contain a reference to this relationship, either in isDecomposedBy or
     * decomposes.
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
    public IfcRelDecomposes(@NotNull IfcGloballyUniqueId globalId,
                            @NotNull IfcOwnerHistory ownerHistory,
                            IfcLabel name, IfcText description,
                            @NotNull IfcObjectDefinition relatingObject,
                            @NotNull IfcObjectDefinition... relatedObjects) {
        this(globalId, ownerHistory, name, description, relatingObject,
                new HashSet<>(Arrays.asList(relatedObjects)));
    }

    /**
     * Creates a new IfcRelDecomposes and generates a pseudo random globalId,
     * and automatically modifies relatingObject and relatedObjects to make them
     * all contain a reference to this relationship, either in isDecomposedBy or
     * decomposes.
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
    public IfcRelDecomposes(@NotNull IfcOwnerHistory ownerHistory,
                            IfcLabel name, IfcText description,
                            @NotNull IfcObjectDefinition relatingObject,
                            @NotNull IfcObjectDefinition... relatedObjects) {
        this(new IfcGloballyUniqueId(), ownerHistory, name, description,
                relatingObject, new HashSet<>(Arrays.asList(relatedObjects)));
    }

    protected IfcObjectDefinition getRelatingObject() {
        return relatingObject;
    }

    /**
     * @return A copy of relatedObjects. Operations performed on this Set don't
     * have any effect on relatedObjects. This is done to prevent its size from
     * becoming zero, and to forbid the addition of relatingObject to
     * relatedObjects.
     */
    protected Set<IfcObjectDefinition> getRelatedObjects() {
        return new HashSet<>(relatedObjects);
    }
}
