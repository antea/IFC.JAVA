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

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

/**
 * The <i>IfcProductDefinitionShape</i> defines all shape relevant information
 * about an <i>IfcProduct</i>. It allows for multiple geometric shape
 * representations of the same product.
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcProductDefinitionShape extends IfcProductRepresentation {

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
     *                                  1, or if it contains objects that are
     *                                  not of type IfcShapeModel.
     */
    public IfcProductDefinitionShape(IfcLabel name,
                                     IfcText description,
                                     @NonNull List<IfcRepresentation> representations) {
        super(name, description, representations);
        for (IfcRepresentation repr : representations) {
            if (!(repr instanceof IfcShapeModel)) {
                throw new IllegalArgumentException(
                        "representations must contain only objects of type " +
                                "IfcShapeModel");
            }
        }
    }

    /**
     * @param name            The word or group of words by which the product
     *                        representation is known.
     * @param description     TheThe <i>IfcProductDefinitionShape</i>
     *                        <p>
     *                        defines all shape relevant information about an
     *                        <i>IfcProduct</i>.
     *                        <p>
     *                        It allows for multiple geometric shape
     *                        representations of the same
     *                        <p>
     *                        product. word or group of words that characterize
     *                        the product representation. It can be used to add
     *                        additional meaning to the name of the product
     *                        representation.
     * @param representations Contained list of representations (including shape
     *                        representations). Each member defines a valid
     *                        representation of a particular type within a
     *                        particular representation context.
     * @throws NullPointerException     If representations is null.
     * @throws IllegalArgumentException If representations' size is lower than
     *                                  1, or if it contains objects that are
     *                                  not of type IfcShapeModel.
     */
    public IfcProductDefinitionShape(IfcLabel name,
                                     IfcText description,
                                     @NonNull IfcRepresentation... representations) {
        this(name, description, Arrays.asList(representations));
    }
}
