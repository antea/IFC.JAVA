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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The <i>IfcProductRepresentation</i> defines a representation of a product,
 * including its (geometric or topological) representation. A product can have
 * zero, one or many geometric representations, and a single geometric
 * representation can be shared among various products using mapped
 * representations.<br>
 */
public class IfcProductRepresentation extends IfcEntity {
    private final IfcLabel name;
    private final IfcText description;
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
     * @throws IllegalArgumentException If representations is null, or if its
     *                                  size is lower than 1.
     */
    public IfcProductRepresentation(IfcLabel name, IfcText description, @NotNull
            List<IfcRepresentation> representations) {
        if (representations == null) {
            throw new IllegalArgumentException("representations cannot be null");
        }
        if (representations.size() < 1) {
            throw new IllegalArgumentException(
                    "size of representations must be at least 1");
        }
        this.name = name;
        this.description = description;
        this.representations = representations;
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
     * @throws IllegalArgumentException If representations is null, or if its
     *                                  size is lower than 1.
     */
    public IfcProductRepresentation(IfcLabel name, IfcText description, @NotNull
            IfcRepresentation... representations) {
        if (representations == null) {
            throw new IllegalArgumentException("representations cannot be " +
                    "null");
        }
        if (representations.length < 1) {
            throw new IllegalArgumentException(
                    "size of representations must be at least 1");
        }
        this.name = name;
        this.description = description;
        this.representations = Arrays.asList(representations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcProductRepresentation that = (IfcProductRepresentation) o;
        return Objects.equals(name, that.name) && Objects.equals(description,
                that.description) &&
                representations.equals(that.representations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, representations);
    }
}
