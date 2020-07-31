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
import lombok.ToString;

/**
 * Dimensionless measure to express ratio values ranging from 0.0 to 1.0.
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcNormalisedRatioMeasure extends IfcRatioMeasure
        implements IfcColourOrFactor {

    /**
     * @param value A non-negative value less than or equal to 1.0.
     * @throws IllegalArgumentException If value is negative or bigger than 1.
     */
    public IfcNormalisedRatioMeasure(double value) {
        super(value);
        if (getValue() < 0 || getValue() > 1) {
            throw new IllegalArgumentException(
                    "value must be non-negative, and less than or equal to 1");
        }
    }
}
