/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 * Modifications Copyright (C) 2021 Antea S.r.l.
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

import buildingsmart.io.Attribute;
import buildingsmart.io.InverseRelationship;
import lombok.NonNull;
import lombok.ToString;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * An <i>IfcObject</i> is the generalization of any semantically treated thing or process. Objects are things as they
 * appear - i.e. occurrences. </p>
 * <blockquote><font size="-1"> NOTE Examples of <i>IfcObject</i>
 * include physically tangible items, such as wall, beam or covering, physically existing items, such as spaces, or
 * conceptual items, such as grids or virtual boundaries. It also stands for processes, such as work tasks, for
 * controls, such as cost items, for actors, such as persons involved in the design process, etc. </font></blockquote>
 * <p>Objects can be named, using the inherited <i>Name</i>
 * attribute, which should be a user recognizable label for the object occurrence. Further explanations to the object
 * can be given using the inherited <i>Description</i> attribute. The <i>ObjectType</i> attribute is used:</p>
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
@ToString(callSuper = true)
public abstract class IfcObject extends IfcObjectDefinition {

    @Attribute(4)
    private final IfcLabel objectType;
    /**
     * Set of relationships to type or property (statically or dynamically defined) information that further define the
     * object. In case of type information, the associated <i>IfcTypeObject</i> contains the specific information (or
     * type, or style), that is common to all instances of <i>IfcObject</i> referring to the same type.
     */
    @InverseRelationship
    private Set<IfcRelDefines> isDefinedBy;

    /**
     * Creates a new IfcObject, using the provided globalId.
     *
     * @param globalId     Assignment of a globally unique identifier within the entire software world.
     * @param ownerHistory Assignment of the information about the current ownership of that object, including owning
     *                     actor, application, local identification and information captured about the recent changes of
     *                     the object, NOTE: only the last modification in stored.
     * @param name         Optional name for use by the participating software systems or users. For some subtypes of
     *                     IfcRoot the insertion of the Name attribute may be required. This would be enforced by a
     *                     where rule.
     * @param description  Optional description, provided for exchanging informative comments.
     * @param objectType   The type denotes a particular type that indicates the object further. The use has to be
     *                     established at the level of instantiable subtypes. In particular it holds the user defined
     *                     type, if the enumeration of the attribute PredefinedType is set to USERDEFINED.
     * @throws NullPointerException     If globalId or ownerHistory are null.
     * @throws IllegalArgumentException If globalId was used in another instance of this class.
     */
    public IfcObject(@NonNull IfcGloballyUniqueId globalId,
                     @NonNull IfcOwnerHistory ownerHistory,
                     IfcLabel name,
                     IfcText description,
                     IfcLabel objectType) {
        super(globalId, ownerHistory, name, description);
        this.objectType = objectType;
    }

    /**
     * Creates a new IfcObject and generates a pseudo random globalId.
     *
     * @param ownerHistory Assignment of the information about the current ownership of that object, including owning
     *                     actor, application, local identification and information captured about the recent changes of
     *                     the object, NOTE: only the last modification in stored.
     * @param name         Optional name for use by the participating software systems or users. For some subtypes of
     *                     IfcRoot the insertion of the Name attribute may be required. This would be enforced by a
     *                     where rule.
     * @param description  Optional description, provided for exchanging informative comments.
     * @param objectType   The type denotes a particular type that indicates the object further. The use has to be
     *                     established at the level of instantiable subtypes. In particular it holds the user defined
     *                     type, if the enumeration of the attribute PredefinedType is set to USERDEFINED.
     * @throws NullPointerException If ownerHistory is null.
     */
    public IfcObject(@NonNull IfcOwnerHistory ownerHistory, IfcLabel name, IfcText description, IfcLabel objectType) {
        this(new IfcGloballyUniqueId(), ownerHistory, name, description, objectType);
    }

    /**
     * @return A copy of {@link #isDefinedBy}. Operations performed on this Set don't have any effect on isDefinedBy. This is
     * done to prevent adding illegal IfcRelDefines to the Set.
     *
     * @see #addToIsDefinedBy(IfcRelDefines)
     */
    public Set<IfcRelDefines> getIsDefinedBy() {
        return isDefinedBy == null ? Collections.emptySet() : Collections.unmodifiableSet(isDefinedBy);
    }

    /**
     * @param relationship The relationship to add to the Set {@link #isDefinedBy}.
     * @throws IllegalArgumentException If {@code relationship} is an instance of {@link IfcRelDefinesByType} and {@link
     *                                  #isDefinedBy} already contains an instance of that type.
     * @throws NullPointerException     If {@code relationship} is {@code null}.
     */
    protected void addToIsDefinedBy(@NonNull IfcRelDefines relationship) {
        if (relationship instanceof IfcRelDefinesByType &&
                getIsDefinedBy().stream().anyMatch(rel -> rel instanceof IfcRelDefinesByType)) {
            throw new IllegalArgumentException(
                    "isDefinedBy can contain at most one relationship of type IfcRelDefinesByType");
        }
        if (this.isDefinedBy != null) {
            this.isDefinedBy.add(relationship);
        } else {
            Set<IfcRelDefines> isDefinedBy = new HashSet<>();
            isDefinedBy.add(relationship);
            this.isDefinedBy = isDefinedBy;
        }
    }
}
