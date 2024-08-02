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
 * A defined type of simple data type REAL. In principle, the domain of IfcReal
 * (being a Real) is all rational, irrational and scientific real numbers.
 */
@EqualsAndHashCode
public class IfcReal implements DefinedType, IfcSimpleValue, Serializable {
    @Getter
    @EqualsAndHashCode.Exclude
    private final double value;
    private final String serialization;

    public IfcReal(double value) {
        if (value == -0d) {
            this.value = 0d;
        } else {
            this.value = value;
        }
        serialization = format(this.value);
    }

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return serialization;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
