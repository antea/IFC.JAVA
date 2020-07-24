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

import java.util.List;
import java.util.Objects;

/**
 * An individual human being.
 */
public class IfcPerson extends IfcEntity {
    @Attribute(0)
    private final IfcIdentifier id;
    @Attribute(1)
    private final IfcLabel familyName;
    @Attribute(2)
    private final IfcLabel givenName;
    @Attribute(3)
    private final List<IfcLabel> middleNames;
    @Attribute(4)
    private final List<IfcLabel> prefixTitles;
    @Attribute(5)
    private final List<IfcLabel> suffixTitles;
    @Attribute(6)
    private final List<IfcActorRole> roles;
    @Attribute(7)
    private final List<IfcAddress> addresses;
    //private IfcPersonAndOrganization[] engagedIn;

    /**
     * @param id           Identification of the person.
     * @param familyName   The name by which the family identity of the person
     *                     may be recognized.
     * @param givenName    The name by which a person is known within a family
     *                     and by which he or she may be familiarly recognized.
     * @param middleNames  Additional names given to a person that enable their
     *                     identification apart from others who may have the
     *                     same or similar family and given names.
     * @param prefixTitles The word, or group of words, which specify the
     *                     person's social and/or professional standing and
     *                     appear before his/her names.
     * @param suffixTitles The word, or group of words, which specify the
     *                     person's social and/or professional standing and
     *                     appear after his/her names.
     * @param roles        Roles played by the person.
     * @param addresses    Postal and telecommunication addresses of a person.
     * @throws IllegalArgumentException If both familyName and givenName are
     *                                  null, or if one of the parameters of
     *                                  type List has size equal to zero.
     */
    public IfcPerson(IfcIdentifier id, IfcLabel familyName, IfcLabel givenName,
                     List<IfcLabel> middleNames, List<IfcLabel> prefixTitles,
                     List<IfcLabel> suffixTitles, List<IfcActorRole> roles,
                     List<IfcAddress> addresses) {
        if (familyName == null && givenName == null) {
            throw new IllegalArgumentException(
                    "familyName and givenName can't be both null, at least " +
                            "one of them should have a value");
        }
        if (middleNames != null && middleNames.size() < 1) {
            throw new IllegalArgumentException(
                    "middleNames must be null or its size must be at least " +
                            "one");
        }
        if (prefixTitles != null && prefixTitles.size() < 1) {
            throw new IllegalArgumentException(
                    "prefixTitles must be null or its size must be at least " +
                            "one");
        }
        if (suffixTitles != null && suffixTitles.size() < 1) {
            throw new IllegalArgumentException(
                    "suffixTitles must be null or its size must be at least " +
                            "one");
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
        this.familyName = familyName;
        this.givenName = givenName;
        this.middleNames = middleNames;
        this.prefixTitles = prefixTitles;
        this.suffixTitles = suffixTitles;
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
        IfcPerson ifcPerson = (IfcPerson) o;
        return Objects.equals(id, ifcPerson.id) &&
                Objects.equals(familyName, ifcPerson.familyName) &&
                Objects.equals(givenName, ifcPerson.givenName) &&
                Objects.equals(middleNames, ifcPerson.middleNames) &&
                Objects.equals(prefixTitles, ifcPerson.prefixTitles) &&
                Objects.equals(suffixTitles, ifcPerson.suffixTitles) &&
                Objects.equals(roles, ifcPerson.roles) &&
                Objects.equals(addresses, ifcPerson.addresses);
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(id, familyName, givenName, middleNames, prefixTitles,
                        suffixTitles, roles, addresses);
    }

    public static final class Builder {
        private IfcIdentifier id;
        private IfcLabel familyName;
        private IfcLabel givenName;
        private List<IfcLabel> middleNames;
        private List<IfcLabel> prefixTitles;
        private List<IfcLabel> suffixTitles;
        private List<IfcActorRole> roles;
        private List<IfcAddress> addresses;

        private Builder() {
        }

        public static Builder anIfcPerson() {
            return new Builder();
        }

        public Builder id(IfcIdentifier id) {
            this.id = id;
            return this;
        }

        public Builder familyName(IfcLabel familyName) {
            this.familyName = familyName;
            return this;
        }

        public Builder givenName(IfcLabel givenName) {
            this.givenName = givenName;
            return this;
        }

        public Builder middleNames(List<IfcLabel> middleNames) {
            this.middleNames = middleNames;
            return this;
        }

        public Builder prefixTitles(List<IfcLabel> prefixTitles) {
            this.prefixTitles = prefixTitles;
            return this;
        }

        public Builder suffixTitles(List<IfcLabel> suffixTitles) {
            this.suffixTitles = suffixTitles;
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

        public IfcPerson build() {
            return new IfcPerson(id, familyName, givenName, middleNames,
                    prefixTitles, suffixTitles, roles, addresses);
        }
    }
}
