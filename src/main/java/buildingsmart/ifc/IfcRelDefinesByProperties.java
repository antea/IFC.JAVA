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
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;

import java.util.Set;

/**
 * <P>This objectified relationship
 * (<I>IfcRelDefinesByProperties</I>) defines the relationships between property set definitions and objects. Properties
 * are aggregated in property sets, property sets can be grouped to define an object type.</P>
 * <P> The <I>IfcRelDefinesByProperties</I> is a 1-to-N relationship, as it
 * allows for the assignment of one property set to a single or to many objects. Those objects then share the same
 * property definition.</P>
 */
@ToString(callSuper = true)
public class IfcRelDefinesByProperties extends IfcRelDefines {

    @Attribute(5)
    private final IfcPropertySetDefinition relatingPropertyDefinition;

    /**
     * Creates a new IfcRelDefinesByProperties and automatically adds it to {@code isDefinedBy} of each {@code relatedObject}'s
     * element.
     *
     * @param globalId                   Assignment of a globally unique identifier within the entire software world.
     * @param ownerHistory               Assignment of the information about the current ownership of that object,
     *                                   including owning actor, application, local identification and information
     *                                   captured about the recent changes of the object, NOTE: only the last
     *                                   modification in stored.
     * @param name                       Optional name for use by the participating software systems or users. For some
     *                                   subtypes of IfcRoot the insertion of the Name attribute may be required. This
     *                                   would be enforced by a where rule.
     * @param description                Optional description, provided for exchanging informative comments.
     * @param relatedObjects             Reference to the objects (or single object) to which the property definition
     *                                   applies.
     * @param relatingPropertyDefinition Reference to the property set definition for that object or set of objects.
     * @throws NullPointerException     If {@code globalId}, {@code ownerHistory} or {@code relatedObjects} is null.
     * @throws IllegalArgumentException If {@code globalId} was used in another instance of this class or its
     *                                  superclass; if {@code relatedObjects} is empty.
     */
    @Builder
    public IfcRelDefinesByProperties(@NonNull IfcGloballyUniqueId globalId,
                                     @NonNull IfcOwnerHistory ownerHistory,
                                     IfcLabel name,
                                     IfcText description,
                                     @NonNull Set<IfcObject> relatedObjects,
                                     @NonNull IfcPropertySetDefinition relatingPropertyDefinition) {
        super(globalId, ownerHistory, name, description, relatedObjects);
        this.relatingPropertyDefinition = relatingPropertyDefinition;
    }
}
