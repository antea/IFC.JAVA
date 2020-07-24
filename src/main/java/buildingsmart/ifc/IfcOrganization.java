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

import buildingsmart.io.Attribute;
import buildingsmart.io.IfcEntity;
import lombok.NonNull;

import java.util.List;
import java.util.Objects;

/**
 * A named and structured grouping with a corporate identity.
 */
public class IfcOrganization extends IfcEntity {
    @Attribute(order = 0)
    private final IfcIdentifier id;
    @Attribute(order = 1)
    private final IfcLabel name;
    @Attribute(order = 2)
    private final IfcText description;
    @Attribute(order = 3)
    private final List<IfcActorRole> roles;
    @Attribute(order = 4)
    private final List<IfcAddress> addresses;
    //private IfcOrganizationRelationship[] IsRelatedBy;
    //private IfcOrganizationRelationship[] Relates;
    //private IfcPersonAndOrganization[] Engages;

    /**
     * @param id          Identification of the organization.
     * @param name        The word, or group of words, by which the organization
     *                    is referred to. Cannot be null.
     * @param description Text that relates the nature of the organization.
     * @param roles       Roles played by the organization.
     * @param addresses   Postal and telecom addresses of an organization.
     * @throws IllegalArgumentException If name is null, or roles or addresses'
     *                                  size is zero.
     */
    public IfcOrganization(IfcIdentifier id, @NonNull IfcLabel name,
                           IfcText description, List<IfcActorRole> roles,
                           List<IfcAddress> addresses) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (roles != null && roles.size() < 1) {
            throw new IllegalArgumentException(
                    "roles must be null or its size must be at least one");
        }
        if (addresses != null && addresses.size() < 1) {
            throw new IllegalArgumentException(
                    "addresses must be null or its size must be at least one");
        }
        this.id = id;
        this.name = name;
        this.description = description;
        this.roles = roles;
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcOrganization that = (IfcOrganization) o;
        return Objects.equals(id, that.id) && name.equals(that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(roles, that.roles) &&
                Objects.equals(addresses, that.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, roles, addresses);
    }

    public static final class Builder {
        private IfcIdentifier id;
        private IfcLabel name;
        private IfcText description;
        private List<IfcActorRole> roles;
        private List<IfcAddress> addresses;

        private Builder() {
        }

        public static Builder anIfcOrganization() {
            return new Builder();
        }

        public Builder id(IfcIdentifier id) {
            this.id = id;
            return this;
        }

        public Builder name(IfcLabel name) {
            this.name = name;
            return this;
        }

        public Builder description(IfcText description) {
            this.description = description;
            return this;
        }

        public Builder roles(List<IfcActorRole> roles) {
            this.roles = roles;
            return this;
        }

        public Builder addresses(List<IfcAddress> addresses) {
            this.addresses = addresses;
            return this;
        }

        public IfcOrganization build() {
            return new IfcOrganization(id, name, description, roles, addresses);
        }
    }
}
