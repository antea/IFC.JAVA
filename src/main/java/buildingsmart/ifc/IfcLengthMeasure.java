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

import java.io.Serializable;

import static buildingsmart.util.Functions.format;

/**
 * A length measure is the value of a distance.
 * <p>
 * Usually measured in millimeters (mm).
 */
@EqualsAndHashCode
public class IfcLengthMeasure implements DefinedType, IfcMeasureValue, IfcSizeSelect, Serializable {
    @Getter
    private final double value;

    /**
     * @param value The value of the distance.
     */
    public IfcLengthMeasure(double value) {
        if (value == -0d) {
            this.value = 0d;
        } else {
            this.value = value;
        }
    }

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return format(value);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
