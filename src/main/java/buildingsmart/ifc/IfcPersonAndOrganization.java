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

import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * Identification of a person within an organization.
 */
public class IfcPersonAndOrganization extends IfcEntity {
    private final IfcPerson thePerson;
    private final IfcOrganization theOrganization;
    private final List<IfcActorRole> roles;

    /**
     * @param thePerson       The person who is related to the organization.
     * @param theOrganization The organization to which the person is related.
     * @param roles           Roles played by the person within the context
     *                        of an organization.
     */
    public IfcPersonAndOrganization(@NotNull IfcPerson thePerson,
                                    @NotNull IfcOrganization theOrganization,
                                    List<IfcActorRole> roles) {
        if (thePerson == null) {
            throw new IllegalArgumentException("thePerson cannot be null");
        }
        if (theOrganization == null) {
            throw new IllegalArgumentException("theOrganization cannot be null");
        }
        if (roles != null && roles.size() < 1) {
            throw new IllegalArgumentException(
                    "role must be null or its size must be at least one");
        }
        this.thePerson = thePerson;
        this.theOrganization = theOrganization;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcPersonAndOrganization that = (IfcPersonAndOrganization) o;
        return thePerson.equals(that.thePerson) && theOrganization.equals(that.theOrganization) &&
                Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thePerson, theOrganization, roles);
    }
}
