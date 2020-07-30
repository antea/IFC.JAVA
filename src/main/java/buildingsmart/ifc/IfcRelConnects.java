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

import lombok.NonNull;
import lombok.ToString;

/**
 * A connectivity relationship (IfcRelConnects) that connects objects under some
 * criteria. As a general connectivity it does not imply constraints, however
 * subtypes of the relationship define the applicable object types for the
 * connectivity relationship and the semantics of the particular connectivity.
 */
@ToString(callSuper = true)
public class IfcRelConnects extends IfcRelationship {
    /**
     * Creates a new IfcRelConnects, using the provided globalId.
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
    public IfcRelConnects(@NonNull IfcGloballyUniqueId globalId,
                          @NonNull IfcOwnerHistory ownerHistory,
                          IfcLabel name,
                          IfcText description) {
        super(globalId, ownerHistory, name, description);
    }

    /**
     * Creates a new IfcRelConnects and generates a pseudo random globalId.
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
    public IfcRelConnects(@NonNull IfcOwnerHistory ownerHistory,
                          IfcLabel name,
                          IfcText description) {
        this(new IfcGloballyUniqueId(), ownerHistory, name, description);
    }
}
