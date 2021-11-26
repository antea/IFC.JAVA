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

import buildingsmart.io.InverseRelationship;
import lombok.NonNull;
import lombok.ToString;

/**
 * <p>The <i>IfcPropertyDefinition</i>
 * defines the generalization of all characteristics (i.e. a grouping of individual properties), that may be assigned to
 * objects. Currently, subtypes of <i>IfcPropertyDefinition</i> include property set definitions, and property sets..
 * </p>
 * <p>Property definitions define information that is shared among
 * multiple instances of objects. The assignment of the shared information to objects is handled by the
 * <i>IfcRelDefines</i> relationship.</p>
 */
@ToString(callSuper = true)
public abstract class IfcPropertyDefinition extends IfcRoot {
    @InverseRelationship
    private final IfcRelAssociates[] HasAssociations;

    /**
     * Creates a new IfcRoot, using the provided globalId.
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
    public IfcPropertyDefinition(@NonNull IfcGloballyUniqueId globalId, @NonNull IfcOwnerHistory ownerHistory,
                                 IfcLabel name,
                                 IfcText description, IfcRelAssociates[] hasAssociations) {
        super(globalId, ownerHistory, name, description);
        HasAssociations = hasAssociations;
    }
}
