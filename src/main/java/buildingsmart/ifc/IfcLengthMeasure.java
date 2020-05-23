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

import java.util.Objects;

/**
 * A length measure is the value of a distance.
 * <p>
 * Usually measured in millimeters (mm).
 */
public class IfcLengthMeasure implements IfcDefinedType, IfcMeasureValue {
    private final double value;

    /**
     * @param value The value of the distance.
     */
    public IfcLengthMeasure(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcLengthMeasure ifcLengthMeasure1 = (IfcLengthMeasure) o;
        return Double.compare(ifcLengthMeasure1.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
