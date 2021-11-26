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
import java.util.stream.Collectors;

/**
 * <p>The <i>IfcPropertySet</i>
 * defines all dynamically extensible properties. The property set is a container class that holds properties within a
 * property tree. These properties are interpreted according to their name attribute. </p>
 * <p>Property sets, defining a particular type of object, can be
 * assigned an object type (<i>IfcTypeObject</i>). Property sets are assigned to objects (<i>IfcObject</i>) through an
 * objectified relationship (<i>IfcRelDefinedByProperties</i>). If the same set of properties applies to more than one
 * object, it should be assigned by a single instance of <i>IfcRelDefinedByProperties</i> to a set of related objects.
 * Those property sets are referred to as shared property sets.</p>
 * <p><b>Use Definition</b></p>
 * <p>Instances of <i>IfcPropertySet</i> are used to
 * assign named sets of individual properties (complex or single properties). Each individual property has a significant
 * name string. Some property sets have&nbsp;predefined instructions on assigning those significant name, these are
 * listed under "property sets" main menu item within this specification. The naming convention "Pset_Xxx" applies to
 * those property sets and shall be used as the value to the <i>Name</i> attribute.</p>
 * <p>In addition any user defined property set can be captured,
 * those property sets shall have a <i>Name</i> value not including the "Pset_" prefix.</p>
 */
@ToString(callSuper = true)
public class IfcPropertySet extends IfcPropertySetDefinition {

    @Attribute(4)
    private final Set<IfcProperty> hasProperties;

    /**
     * @param globalId      Assignment of a globally unique identifier within the entire software world.
     * @param ownerHistory  Assignment of the information about the current ownership of that object, including owning
     *                      actor, application, local identification and information captured about the recent changes
     *                      of the object, NOTE: only the last modification in stored.
     * @param name          Used to specify the type of the property set definition. The property set structure for
     *                      particular property sets may be given within the property set definition part of the IFC
     *                      specification.
     * @param description   Optional description, provided for exchanging informative comments.
     * @param hasProperties Contained set of properties. For property sets defined as part of the IFC Object model, the
     *                      property objects within a property set are defined as part of the standard. If a property is
     *                      not contained within the set of predefined properties, its value has not been set at this
     *                      time.
     * @throws NullPointerException     If {@code globalId}, {@code ownerHistory} or {@code name} is null.
     * @throws IllegalArgumentException If {@code globalId} was used in another instance of this class; if {@code
     *                                  hasProperties} is empty; if {@code hasProperties} has multiple properties with
     *                                  the same name.
     */
    @Builder
    public IfcPropertySet(@NonNull IfcGloballyUniqueId globalId,
                          @NonNull IfcOwnerHistory ownerHistory,
                          @NonNull IfcLabel name,
            IfcText description,
            @NonNull Set<IfcProperty> hasProperties, IfcRelAssociates[] hasAssociations,
            IfcRelDefinesByProperties[] propertyDefinitionOf) {
        super(globalId, ownerHistory, name, description, hasAssociations, propertyDefinitionOf);

        if (hasProperties.isEmpty()) {
            throw new IllegalArgumentException("hasProperties must contain at least 1 element");
        }

        Set<String> propertiesNames =
                hasProperties.stream().map(prop -> prop.getName().getValue()).collect(Collectors.toSet());
        if (propertiesNames.size() != hasProperties.size()) {
            throw new IllegalArgumentException("names of properties in hasProperties must be unique");
        }

        this.hasProperties = hasProperties;
    }
}
