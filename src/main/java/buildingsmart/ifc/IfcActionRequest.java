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

public class IfcActionRequest extends IfcControl {
    private String RequestID;

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
     * @param objectType   The type denotes a particular type that indicates the
     *                     object further. The use has to be established at the
     *                     level of instantiable subtypes. In particular it
     *                     holds the user defined type, if the enumeration of
     *                     the attribute PredefinedType is set to USERDEFINED.
     * @throws IllegalArgumentException If globalId or ownerHistory are null, or
     *                                  if globalId was used in another instance
     *                                  of this class.
     */
    public IfcActionRequest(IfcGloballyUniqueId globalId,
                            IfcOwnerHistory ownerHistory, IfcLabel name,
                            IfcText description, IfcLabel objectType) {
        super(globalId, ownerHistory, name, description, objectType);
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
     * @param objectType   The type denotes a particular type that indicates the
     *                     object further. The use has to be established at the
     *                     level of instantiable subtypes. In particular it
     *                     holds the user defined type, if the enumeration of
     *                     the attribute PredefinedType is set to USERDEFINED.
     * @throws IllegalArgumentException If ownerHistory is null.
     */
    public IfcActionRequest(IfcOwnerHistory ownerHistory, IfcLabel name,
                            IfcText description, IfcLabel objectType) {
        super(ownerHistory, name, description, objectType);
    }
}
