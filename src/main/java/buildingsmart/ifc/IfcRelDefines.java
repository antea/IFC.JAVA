/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 * Modifications Copyright (C) 2021 Antea S.r.l.
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
import lombok.NonNull;
import lombok.ToString;

import java.util.Set;

/**
 * <P>A definition relationship
 * (<I>IfcRelDefines</I>) that uses a type definition or property set definition (seens as partial type information) to
 * define the properties of the object instance. It is a specific - occurrence relationship with implied dependencies
 * (as the occurrence properties depend on the specific properties).</P>
 * <P>The <I>IfcRelDefines</I> relationship establishes the link between one
 * type (specific) information and several objects (occurrences). Those occurrences then share the same type (or partial
 * type) information.</P>
 * <BLOCKQUOTE><FONT SIZE="-1">EXAMPLE: Several instances of windows within
 * the IFC project model may be of the same (catalogue or manufacturer) type. Thereby they share the same properties.
 * This relationship is established by a subtype of the <I>IfcRelDefines</I> relationship assigning an
 * <I>IfcProductType</I> (or subtype thereof) to the
 * <I>IfcWindow</I>.</FONT></BLOCKQUOTE>
 */
@ToString(callSuper = true)
public abstract class IfcRelDefines extends IfcRelationship {

    @Attribute(4)
    private final Set<IfcObject> relatedObjects;

    /**
     * Creates a new IfcRelDefines and automatically adds it to {@code isDefinedBy} of each {@code relatedObject}'s
     * element.
     *
     * @param globalId       Assignment of a globally unique identifier within the entire software world.
     * @param ownerHistory   Assignment of the information about the current ownership of that object, including owning
     *                       actor, application, local identification and information captured about the recent changes
     *                       of the object, NOTE: only the last modification in stored.
     * @param name           Optional name for use by the participating software systems or users. For some subtypes of
     *                       IfcRoot the insertion of the Name attribute may be required. This would be enforced by a
     *                       where rule.
     * @param description    Optional description, provided for exchanging informative comments.
     * @param relatedObjects Reference to the objects (or single object) to which the property definition applies.
     * @throws NullPointerException     If {@code globalId}, {@code ownerHistory} or {@code relatedObjects} is null.
     * @throws IllegalArgumentException If {@code globalId} was used in another instance of this class or its
     *                                  superclass; if {@code relatedObjects} is empty.
     */
    public IfcRelDefines(@NonNull IfcGloballyUniqueId globalId,
                         @NonNull IfcOwnerHistory ownerHistory,
                         IfcLabel name,
                         IfcText description,
                         @NonNull Set<IfcObject> relatedObjects) {
        super(globalId, ownerHistory, name, description);
        if (relatedObjects.isEmpty()) {
            throw new IllegalArgumentException("relatedObjects must contain at least 1 element");
        }
        relatedObjects.forEach(obj -> obj.addToIsDefinedBy(this));
        this.relatedObjects = relatedObjects;
    }
}
