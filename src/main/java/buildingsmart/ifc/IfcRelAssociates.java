/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 *
 * This file is part of ifc-java.
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

import lombok.NonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The association relationship (<I>IfcRelAssociates</I>) refer to external
 * sources of information (most notably a classification, library or document).
 * There is no dependency implied by the association.</P>
 * <BLOCKQUOTE><FONT SIZE="-1">EXAMPLE: Further information may the
 * given to the tank equipment (as subtype of <I>IfcProduct</I>) in terms of its
 * classification and instruction documents, the source of the additional
 * information is held external to the IFC project model.
 * </FONT></BLOCKQUOTE>
 * <P>Association relationships can the established to objects
 * (occurrences) or to types (both object types, <I>IfcTypeObject</I>, or
 * partial types,
 * <I>IfcPropertySetDefinition</I>).</P>
 * <BLOCKQUOTE><FONT SIZE="-1">EXAMPLE 1: The classification information
 * for the tank equipment may be associated to the
 * <I>IfcTypeProduct</I> (subtype of
 * <I>IfcTypeObject</I>), defining the specific information for
 * all occurrences of that tank in the project. Therefore the association of the
 * (e .g.) Uniclass notation 'L6814' may be associated by a subtype of
 * <I>IfcRelAssociates</I> to
 * the type information.</FONT></BLOCKQUOTE>
 * <BLOCKQUOTE><FONT SIZE="-1">EXAMPLE 2: The classification information
 * for a particular space within a building may the associated to the
 * <I>IfcSpace</I>
 * object (subtype of <I>IfcObject</I>), defining a particular occurrence of
 * space. Therefore the association of the (e.g.) DIN notation 'HNF 1.5' may be
 * associated by a subtype of <I>IfcRelAssociates</I> to the
 * object.</FONT></BLOCKQUOTE>
 * <P>The association relationship establishs a uni-directional
 * association. The subtypes of <I>IfcRelAssociates</I> establishes the
 * particular semantic meaning of the association relationship.</P>
 */
public class IfcRelAssociates extends IfcRelationship {
    private final Set<IfcRoot> relatedObjects;

    /**
     * Creates a new IfcRelAssociates, using the provided globalId, and
     * automatically adds this relationship to attribute hasAssociations for
     * each object in relatedObjects that is an instance of
     * IfcObjectDefinition.
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
     * @param relatedObjects Objects or Types, to which the external references
     *                       or information is associated.
     * @throws NullPointerException     If globalId, ownerHistory or
     *                                  relatedObjects is null.
     * @throws IllegalArgumentException If globalId was used in another instance
     *                                  of this class or its superclass, if
     *                                  relatedObjects has size lower than 1, if
     *                                  relatedObjects contains objects that are
     *                                  not of type IfcObjectDefinition nor
     *                                  IfcPropertyDefinition.
     */
    public IfcRelAssociates(@NonNull IfcGloballyUniqueId globalId,
                            @NonNull IfcOwnerHistory ownerHistory,
                            IfcLabel name,
                            IfcText description,
                            @NonNull Set<IfcRoot> relatedObjects) {
        super(globalId, ownerHistory, name, description);
        if (relatedObjects.isEmpty()) {
            throw new IllegalArgumentException(
                    "size of relatedObjects must be at least 1");
        }
        for (IfcRoot obj : relatedObjects) {
            if (!(obj instanceof IfcObjectDefinition ||
                    obj instanceof IfcPropertyDefinition)) {
                throw new IllegalArgumentException(
                        "relatedObjects must only contain objects of type " +
                                "IfcObjectDefinition and " +
                                "IfcPropertyDefinition");
            }
        }
        this.relatedObjects = relatedObjects;
        for (IfcRoot obj : relatedObjects) {
            if (obj instanceof IfcObjectDefinition) {
                ((IfcObjectDefinition) obj).addToHasAssociations(this);
            }
        }
    }

    /**
     * Creates a new IfcRelAssociates, generates a pseudo random globalId, and
     * automatically adds this relationship to attribute hasAssociations for
     * each object in relatedObjects that is an instance of
     * IfcObjectDefinition.
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
     * @param relatedObjects Objects or Types, to which the external references
     *                       or information is associated.
     * @throws NullPointerException     If globalId or relatedObjects is null.
     * @throws IllegalArgumentException If relatedObjects has size lower than 1,
     *                                  if relatedObjects contains objects that
     *                                  are not of type IfcObjectDefinition nor
     *                                  IfcPropertyDefinition.
     */
    public IfcRelAssociates(@NonNull IfcOwnerHistory ownerHistory,
                            IfcLabel name,
                            IfcText description,
                            @NonNull Set<IfcRoot> relatedObjects) {
        this(new IfcGloballyUniqueId(),
             ownerHistory,
             name,
             description,
             relatedObjects);
    }

    /**
     * Creates a new IfcRelAssociates, using the provided globalId, and
     * automatically adds this relationship to attribute hasAssociations for
     * each object in relatedObjects that is an instance of
     * IfcObjectDefinition.
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
     * @param relatedObjects Objects or Types, to which the external references
     *                       or information is associated.
     * @throws NullPointerException     If globalId, ownerHistory or
     *                                  relatedObjects is null.
     * @throws IllegalArgumentException If globalId was used in another instance
     *                                  of this class or its superclass, if
     *                                  relatedObjects has size lower than 1, if
     *                                  relatedObjects contains objects that are
     *                                  not of type IfcObjectDefinition nor
     *                                  IfcPropertyDefinition.
     */
    public IfcRelAssociates(@NonNull IfcGloballyUniqueId globalId,
                            @NonNull IfcOwnerHistory ownerHistory,
                            IfcLabel name,
                            IfcText description,
                            @NonNull IfcRoot... relatedObjects) {
        this(globalId,
             ownerHistory,
             name,
             description,
             new HashSet<>(Arrays.asList(relatedObjects)));
    }

    /**
     * Creates a new IfcRelAssociates, generates a pseudo random globalId, and
     * automatically adds this relationship to attribute hasAssociations for
     * each object in relatedObjects that is an instance of
     * IfcObjectDefinition.
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
     * @param relatedObjects Objects or Types, to which the external references
     *                       or information is associated.
     * @throws NullPointerException     If globalId or relatedObjects is null.
     * @throws IllegalArgumentException If relatedObjects has size lower than 1,
     *                                  if relatedObjects contains objects that
     *                                  are not of type IfcObjectDefinition nor
     *                                  IfcPropertyDefinition.
     */
    public IfcRelAssociates(@NonNull IfcOwnerHistory ownerHistory,
                            IfcLabel name,
                            IfcText description,
                            @NonNull IfcRoot... relatedObjects) {
        this(new IfcGloballyUniqueId(),
             ownerHistory,
             name,
             description,
             relatedObjects);
    }

    /**
     * @return A copy of relatedObjects. Operations performed on this Set don't
     * have any effect on relatedObjects. This is done to prevent its size from
     * becoming zero, and to forbid the addition of relatingObject to
     * relatedObjects.
     */
    protected Set<IfcRoot> getRelatedObjects() {
        return new HashSet<>(relatedObjects);
    }
}
