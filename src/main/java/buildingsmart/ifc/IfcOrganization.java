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
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

/**
 * A named and structured grouping with a corporate identity.
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcOrganization extends Entity {
    @Attribute(0)
    private final IfcIdentifier id;
    @Attribute(1)
    private final IfcLabel name;
    @Attribute(2)
    private final IfcText description;
    @Attribute(3)
    private final List<IfcActorRole> roles;
    @Attribute(4)
    private final List<IfcAddress> addresses;

    /**
     * @param id          Identification of the organization.
     * @param name        The word, or group of words, by which the organization
     *                    is referred to. Cannot be null.
     * @param description Text that relates the nature of the organization.
     * @param roles       Roles played by the organization.
     * @param addresses   Postal and telecom addresses of an organization.
     * @throws NullPointerException     If name is null.
     * @throws IllegalArgumentException If name is null, or roles or addresses'
     *                                  size is zero.
     */
    @Builder
    public IfcOrganization(IfcIdentifier id,
                           @NonNull IfcLabel name,
                           IfcText description,
                           List<IfcActorRole> roles,
                           List<IfcAddress> addresses) {
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
}
