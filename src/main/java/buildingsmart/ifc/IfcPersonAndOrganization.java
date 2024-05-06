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

import buildingsmart.io.Attribute;
import buildingsmart.io.Entity;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

/**
 * Identification of a person within an organization.
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcPersonAndOrganization extends Entity {
    @Attribute(0)
    private final IfcPerson thePerson;
    @Attribute(1)
    private final IfcOrganization theOrganization;
    @Attribute(2)
    private final List<IfcActorRole> roles;

    /**
     * @param thePerson       The person who is related to the organization.
     * @param theOrganization The organization to which the person is related.
     * @param roles           Roles played by the person within the context of
     *                        an organization.
     * @throws NullPointerException     If thePerson or theOrganization is
     *                                  null.
     * @throws IllegalArgumentException If roles is not null and has size lower
     *                                  than 1.
     */
    public IfcPersonAndOrganization(@NonNull IfcPerson thePerson,
                                    @NonNull IfcOrganization theOrganization,
                                    List<IfcActorRole> roles) {
        if (roles != null && roles.isEmpty()) {
            throw new IllegalArgumentException(
                    "role must be null or its size must be at least one");
        }
        this.thePerson = thePerson;
        this.theOrganization = theOrganization;
        this.roles = roles;
    }
}
