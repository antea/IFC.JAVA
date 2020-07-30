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
import buildingsmart.util.Functions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * A named unit is a unit quantity associated with the word, or group of words,
 * by which the unit is identified.
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public abstract class IfcNamedUnit extends IfcEntity implements IfcUnit {
    @Attribute(0)
    private final IfcDimensionalExponents dimensions;
    @Getter
    @Attribute(1)
    private final IfcUnitEnum unitType;

    /**
     * @param dimensions The dimensional exponents of the SI base units by which
     *                   the named unit is defined.
     * @param unitType   The type of the unit.
     * @throws NullPointerException     If any of the parameters are {@code
     *                                  null}.
     * @throws IllegalArgumentException If dimensions is wrong for the given
     *                                  unitType.
     * @see Functions#ifcCorrectDimensions(IfcUnitEnum, IfcDimensionalExponents)
     */
    public IfcNamedUnit(@NonNull IfcDimensionalExponents dimensions,
                        @NonNull IfcUnitEnum unitType) {
        if (Boolean.FALSE
                .equals(Functions.ifcCorrectDimensions(unitType, dimensions))) {
            throw new IllegalArgumentException(
                    "given dimensions for this unitType are wrong");
        }
        this.dimensions = dimensions;
        this.unitType = unitType;
    }
}
