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

import buildingsmart.io.InverseRelationship;
import lombok.NonNull;
import lombok.ToString;

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
@ToString(callSuper = true)
public abstract class IfcObjectDefinition extends IfcRoot {
    /**
     * Reference to the relationship objects, that associates external
     * references or other resource definitions to the object.. Examples are the
     * association to library, documentation or classification.
     */
    @InverseRelationship
    protected Set<IfcRelAssociates> hasAssociations;
    /**
     * Reference to the decomposition relationship, that allows this object to
     * be the composition of other objects. An object can be decomposed by
     * several other objects.
     */
    @InverseRelationship
    private Set<IfcRelDecomposes> isDecomposedBy;
    /**
     * References to the decomposition relationship, that allows this object to
     * be a part of the decomposition. An object can only be part of a single
     * decomposition (to allow hierarchical strutures only).
     */
    @InverseRelationship
    private IfcRelDecomposes decomposes;

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
     * @throws NullPointerException     If globalId or ownerHistory are null.
     * @throws IllegalArgumentException If globalId was used in another instance
     *                                  of this class.
     */
    public IfcObjectDefinition(@NonNull IfcGloballyUniqueId globalId,
                               @NonNull IfcOwnerHistory ownerHistory,
                               IfcLabel name,
                               IfcText description) {
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
     * @throws NullPointerException If ownerHistory is null.
     */
    public IfcObjectDefinition(@NonNull IfcOwnerHistory ownerHistory,
                               IfcLabel name,
                               IfcText description) {
        super(ownerHistory, name, description);
    }

    /**
     * @return A copy of isDecomposedBy. Operations performed on this Set don't
     * have any effect on isDecomposedBy. This is done to prevent adding illegal
     * IfcRelDecomposes to the Set.
     *
     * @see #addToIsDecomposedBy(IfcRelDecomposes)
     */
    protected Set<IfcRelDecomposes> getIsDecomposedBy() {
        return isDecomposedBy == null ? new HashSet<>(0) :
                new HashSet<>(isDecomposedBy);
    }

    /**
     * @param relationship The relationship to add to the Set isDecomposedBy.
     * @throws IllegalArgumentException If this object is not the relatingObject
     *                                  in the relationship.
     * @throws NullPointerException     If relationship is null.
     */
    protected void addToIsDecomposedBy(@NonNull IfcRelDecomposes relationship) {
        if (!relationship.getRelatingObject().equals(this)) {
            throw new IllegalArgumentException(
                    "any IfcRelDecomposes part of isDecomposedBy must " +
                            "have this instance of IfcObjectDefinition as its" +
                            " relatingObject");
        }
        if (this.isDecomposedBy != null) {
            this.isDecomposedBy.add(relationship);
        } else {
            Set<IfcRelDecomposes> isDecomposedBy = new HashSet<>();
            isDecomposedBy.add(relationship);
            this.isDecomposedBy = isDecomposedBy;
        }
    }

    protected IfcRelDecomposes getDecomposes() {
        return decomposes;
    }

    /**
     * @param decomposes References to the decomposition relationship, that
     *                   allows this object to be a part of the decomposition.
     *                   An object can only be part of a single decomposition
     *                   (to allow hierarchical strutures only).
     * @throws IllegalArgumentException If decomposes.relatedObjects does not
     *                                  contain this object.
     * @throws NullPointerException     If decomposes is null.
     */
    protected void setDecomposes(@NonNull IfcRelDecomposes decomposes) {
        if (!decomposes.getRelatedObjects().contains(this)) {
            throw new IllegalArgumentException(
                    "decomposes.relatedObjects must contain this object");
        }
        this.decomposes = decomposes;
    }

    /**
     * @return A copy of hasAssociations. Operations performed on this Set don't
     * have any effect on hasAssociations. This is done to prevent adding
     * illegal IfcRelDecomposes to the Set.
     *
     * @see #addToHasAssociations(IfcRelAssociates)
     */
    protected Set<IfcRelAssociates> getHasAssociations() {
        return hasAssociations == null ? new HashSet<>(0) :
                new HashSet<>(hasAssociations);
    }

    /**
     * @param relationship The relationship to add to the Set hasAssociations.
     * @throws IllegalArgumentException If this object is not contained in the
     *                                  relationship's relatedObjects.
     * @throws NullPointerException     If relationship is null.
     */
    protected void addToHasAssociations(@NonNull IfcRelAssociates relationship) {
        if (!relationship.getRelatedObjects().contains(this)) {
            throw new IllegalArgumentException(
                    "this object must be contained in relationship" +
                            ".relatedObject");
        }
        if (this.hasAssociations != null) {
            this.hasAssociations.add(relationship);
        } else {
            Set<IfcRelAssociates> hasAssociations = new HashSet<>();
            hasAssociations.add(relationship);
            this.hasAssociations = hasAssociations;
        }
    }
}
