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
import lombok.ToString;

/**
 * The abstract generalization of all objectified relationships in IFC.
 * Objectified relationships are the preferred way to handle relationships among
 * objects. This allows to keep relationship specific properties directly at the
 * relationship and opens the possibility to later handle relationship specific
 * behavior. </P>
 * <P>There are two different types of relationships, 1-to-1
 * relationships and 1-to-many relationship. used within the subtypes of
 * <I>IfcRelationship</I>. The
 * following convention applies to all subtypes:</P>
 * <UL>
 * <LI>The two sides of the objectified relationship are named
 * <BR>-
 * Relating+&lt;name of relating object&gt; and <BR>- Related+&lt;name of
 * related object&gt;</LI>
 * <LI>In case of the 1-to-many relationship, the related side of
 * the relationship shall be an aggregate SET 1:N</LI>
 * </UL>
 */
@ToString(callSuper = true)
public abstract class IfcRelationship extends IfcRoot {
    /**
     * Creates a new IfcRelationship, using the provided globalId.
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
     *                                  of this class or its superclass.
     */
    public IfcRelationship(@NonNull IfcGloballyUniqueId globalId,
                           @NonNull IfcOwnerHistory ownerHistory,
                           IfcLabel name,
                           IfcText description) {
        super(globalId, ownerHistory, name, description);
    }

    /**
     * Creates a new IfcRelationship and generates a pseudo random globalId.
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
    public IfcRelationship(@NonNull IfcOwnerHistory ownerHistory,
                           IfcLabel name,
                           IfcText description) {
        super(ownerHistory, name, description);
    }
}
