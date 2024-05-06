/*
 * Copyright (C) 2019 Pieter Pauwels, Ghent University
 * Modifications Copyright (C) 2020 Giovanni Velludo
 * Modifications Copyright (C) 2022 Antea S.r.l.
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

import java.util.Arrays;
import java.util.List;

/**
 * The <i>IfcProductRepresentation</i> defines a representation of a product,
 * including its (geometric or topological) representation. A product can have
 * zero, one or many geometric representations, and a single geometric
 * representation can be shared among various products using mapped
 * representations.<br>
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public class IfcProductRepresentation extends Entity {
    @Attribute(0)
    private final IfcLabel name;
    @Attribute(1)
    private final IfcText description;
    @Attribute(2)
    private final List<IfcRepresentation> representations;

    /**
     * @param name            The word or group of words by which the product
     *                        representation is known.
     * @param description     The word or group of words that characterize the
     *                        product representation. It can be used to add
     *                        additional meaning to the name of the product
     *                        representation.
     * @param representations Contained list of representations (including shape
     *                        representations). Each member defines a valid
     *                        representation of a particular type within a
     *                        particular representation context.
     * @throws NullPointerException     If representations is null.
     * @throws IllegalArgumentException If representations' size is lower than
     *                                  1.
     */
    public IfcProductRepresentation(IfcLabel name,
                                    IfcText description,
                                    @NonNull List<IfcRepresentation> representations) {
        if (representations.isEmpty()) {
            throw new IllegalArgumentException(
                    "size of representations must be at least 1");
        }
        this.name = name;
        this.description = description;
        this.representations = representations;
        for (IfcRepresentation repr : representations) {
            repr.setOfProductRepresentation(this);
        }
    }

    /**
     * @param name            The word or group of words by which the product
     *                        representation is known.
     * @param description     The word or group of words that characterize the
     *                        product representation. It can be used to add
     *                        additional meaning to the name of the product
     *                        representation.
     * @param representations Contained list of representations (including shape
     *                        representations). Each member defines a valid
     *                        representation of a particular type within a
     *                        particular representation context.
     * @throws NullPointerException     If representations is null.
     * @throws IllegalArgumentException If representations' size is lower than
     *                                  1.
     */
    public IfcProductRepresentation(IfcLabel name,
                                    IfcText description,
                                    @NonNull IfcRepresentation... representations) {
        if (representations.length < 1) {
            throw new IllegalArgumentException("size of representations must be at least 1");
        }
        this.name = name;
        this.description = description;
        this.representations = Arrays.asList(representations);
        for (IfcRepresentation repr : representations) {
            repr.setOfProductRepresentation(this);
        }
    }
}
