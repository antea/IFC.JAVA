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
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * An individual human being.
 */
@EqualsAndHashCode(callSuper = false)
@ToString
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
    @Builder
    public IfcPerson(IfcIdentifier id,
                     IfcLabel familyName,
                     IfcLabel givenName,
                     List<IfcLabel> middleNames,
                     List<IfcLabel> prefixTitles,
                     List<IfcLabel> suffixTitles,
                     List<IfcActorRole> roles,
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
}
