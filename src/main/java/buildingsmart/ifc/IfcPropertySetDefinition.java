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
 * <p>An <i>IfcPropertySetDefinition</i>
 * is a generalization of property sets, that are either: </p>
 * <blockquote>
 * <p><b>Dynamically extendable properties</b>, they
 * define properties for which the IFC model only provides a kind of "meta model", to be further declared by agreement.
 * This means no entity definition of the properties exists within the IFC model. The declaration is done by assigning a
 * significant string value to the&nbsp;<i>Name</i> attribute of the entity as defined in the entity
 * <i>IfcPropertySet</i> and at each subtype of
 * <i>IfcProperty</i>, referenced by the property set.</p>
 * <p><b>Statically defined properties</b>, they
 * define properties for which an entity definition exists within the IFC model. The semantic meaning of each statically
 * defined property is declared by its entity type and the meaning of the properties is defined by the name of the
 * explicit attribute.</p>
 * </blockquote>
 * <p>The subtypes of the <i>IfcPropertySetDefinition</i>
 * are either the dynamically extendable <i>IfcPropertySet</i>, or all other statically defined subtypes.</p>
 */
@ToString(callSuper = true)
public abstract class IfcPropertySetDefinition extends IfcPropertyDefinition {
    @InverseRelationship
    private final IfcRelDefinesByProperties[] PropertyDefinitionOf;
    @InverseRelationship
    private IfcTypeObject[] DefinesType;

    /**
     * @param globalId     Assignment of a globally unique identifier within the entire software world.
     * @param ownerHistory Assignment of the information about the current ownership of that object, including owning
     *                     actor, application, local identification and information captured about the recent changes of
     *                     the object, NOTE: only the last modification in stored.
     * @param name         Optional name for use by the participating software systems or users. For some subtypes of
     *                     IfcRoot the insertion of the Name attribute may be required. This would be enforced by a
     *                     where rule.
     * @param description  Optional description, provided for exchanging informative comments.
     * @throws NullPointerException     If {@code globalId} or {@code ownerHistory} is null.
     * @throws IllegalArgumentException If {@code globalId} was used in another instance of this class.
     */
    public IfcPropertySetDefinition(@NonNull IfcGloballyUniqueId globalId,
                                    @NonNull IfcOwnerHistory ownerHistory, IfcLabel name,
                                    IfcText description, IfcRelAssociates[] hasAssociations,
                                    IfcRelDefinesByProperties[] propertyDefinitionOf) {
        super(globalId, ownerHistory, name, description, hasAssociations);
        PropertyDefinitionOf = propertyDefinitionOf;
    }
}
