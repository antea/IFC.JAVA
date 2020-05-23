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

import buildingsmart.util.Functions;
import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * A named unit is a unit quantity associated with the word, or group of words,
 * by which the unit is identified.
 */
public abstract class IfcNamedUnit extends IfcEntity implements IfcUnit {
    private final IfcDimensionalExponents dimensions;
    private final IfcUnitEnum unitType;

    //TODO: test constructor

    /**
     * @param dimensions The dimensional exponents of the SI base units by which
     *                   the named unit is defined.
     * @param unitType   The type of the unit.
     * @throws IllegalArgumentException If any of the parameters are {@code
     *                                  null}, or if dimensions is wrong for the
     *                                  given unitType.
     * @see Functions#ifcCorrectDimensions(IfcUnitEnum, IfcDimensionalExponents)
     */
    public IfcNamedUnit(@NotNull IfcDimensionalExponents dimensions,
                        @NotNull IfcUnitEnum unitType) {
        if (dimensions == null) {
            throw new IllegalArgumentException("dimensions cannot be null");
        }
        if (unitType == null) {
            throw new IllegalArgumentException("unitType cannot be null");
        }
        try {
            if (!Functions.ifcCorrectDimensions(unitType, dimensions)) {
                throw new IllegalArgumentException(
                        "given dimensions for this unitType are wrong");
            }
        } catch (NullPointerException e) {
            // unitType is USERDEFINED, so we can't check if dimensions is
            // correct
        }
        this.dimensions = dimensions;
        this.unitType = unitType;
    }

    public IfcUnitEnum getUnitType() {
        return unitType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcNamedUnit that = (IfcNamedUnit) o;
        return dimensions.equals(that.dimensions) && unitType == that.unitType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dimensions, unitType);
    }
}
