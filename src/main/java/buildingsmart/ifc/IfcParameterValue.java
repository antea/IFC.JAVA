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

import static buildingsmart.util.Functions.format;

/**
 * A parameter value is the value which specifies the amount of a parameter in
 * some parameter space.
 */
@EqualsAndHashCode
public class IfcParameterValue
        implements DefinedType, IfcMeasureValue, IfcTrimmingSelect {
    @EqualsAndHashCode.Exclude
    private final double value;
    private final String serialization;

    public IfcParameterValue(double value) {
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
