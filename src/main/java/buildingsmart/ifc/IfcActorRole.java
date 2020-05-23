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

import java.util.Objects;

/**
 * A role which is performed by an actor, either a person, an organization or
 * a person related to an organization.
 */
public class IfcActorRole {
    private final IfcRoleEnum role;
    private final IfcLabel userDefinedRole;
    private final IfcText description;

    /**
     * @param role            Cannot be null.
     * @param userDefinedRole If role is set to USERDEFINED, then this cannot
     *                        be null. In any other case this parameter should
     *                        be null.
     * @param description     A textual description relating the nature of
     *                        the role played by an actor.
     * @throws IllegalArgumentException If role is null, or there's an
     *                                  invalid combination of role and
     *                                  userDefinedRole.
     */
    public IfcActorRole(@NotNull IfcRoleEnum role, IfcLabel userDefinedRole,
                        IfcText description) {
        if (role == null) {
            throw new IllegalArgumentException("role cannot be null");
        }
        if (role == IfcRoleEnum.USERDEFINED && userDefinedRole == null) {
            throw new IllegalArgumentException("if role is set to " +
                    "USERDEFINED, then userDefinedRole cannot be null");
        }
        if (role != IfcRoleEnum.USERDEFINED && userDefinedRole != null) {
            throw new IllegalArgumentException("if role is not set to " +
                    "USERDEFINED, then userDefinedRole must be null");
        }
        this.role = role;
        this.userDefinedRole = userDefinedRole;
        this.description = description;
    }

    public IfcRoleEnum getRole() {
        return role;
    }

    public IfcLabel getUserDefinedRole() {
        return userDefinedRole;
    }

    public IfcText getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcActorRole that = (IfcActorRole) o;
        return role == that.role &&
                Objects.equals(userDefinedRole, that.userDefinedRole) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, userDefinedRole, description);
    }
}
