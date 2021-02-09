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

import buildingsmart.io.DefinedType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * A dimension count is a positive integer used to define the coordinate space
 * dimensionality.
 */
@EqualsAndHashCode
@ToString
public class IfcDimensionCount implements DefinedType, Serializable {
    @Getter
    private final byte value;

    /**
     * @param value The positive integer used to define the coordinate space
     *              dimensionality.
     * @throws IllegalArgumentException If value is not 1, 2 or 3.
     */
    public IfcDimensionCount(byte value) {
        if (value <= 0 || value > 3) {
            throw new IllegalArgumentException(
                    "value must be equal to 1, 2 or 3");
        }
        this.value = value;
    }

    /**
     * @param value The positive integer used to define the coordinate space
     *              dimensionality.
     * @throws IllegalArgumentException If value is not 1, 2 or 3.
     */
    public IfcDimensionCount(int value) {
        if (value <= 0 || value > 3) {
            throw new IllegalArgumentException(
                    "value must be equal to 1, 2 or 3");
        }
        this.value = (byte) value;
    }

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return Integer.toString(value);
    }
}
