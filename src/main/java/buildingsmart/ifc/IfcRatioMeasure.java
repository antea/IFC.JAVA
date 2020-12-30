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

import buildingsmart.io.DefinedType;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A ratio measure is the value of the relation between two physical quantities
 * that are of the same kind.
 * <p>
 * <BLOCKQUOTE><FONT SIZE="-1">
 * NOTE: Input given in percent is to be divided by 100% when   stored as an
 * IfcRatioMeasure. E.g. 25% becomes 0.25.
 * </FONT>
 * </BLOCKQUOTE>
 */
@EqualsAndHashCode
@ToString
public class IfcRatioMeasure implements DefinedType, IfcMeasureValue, IfcSizeSelect {
    @Getter(value = AccessLevel.PROTECTED)
    private final double value;

    public IfcRatioMeasure(double value) {
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
        return Double.toString(value);
    }
}
