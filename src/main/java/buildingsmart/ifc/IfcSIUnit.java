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
import buildingsmart.io.DerivedAttributes;
import buildingsmart.util.Functions;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * An SI unit is the fixed quantity used as a standard in terms of which items
 * are measured as defined by ISO 1000 (clause 2).
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DerivedAttributes("dimensions")
public class IfcSIUnit extends IfcNamedUnit {
    @Attribute(2)
    private final IfcSIPrefix prefix;
    @Attribute(3)
    private final IfcSIUnitName name;

    /**
     * @param unitType The type of the unit.
     * @param prefix   The SI Prefix for defining decimal multiples and
     *                 submultiples of the unit.
     * @param name     The word, or group of words, by which the SI unit is
     *                 referred to.
     * @throws NullPointerException     If unitType or name are null.
     * @throws IllegalArgumentException If the given unitType is not the
     *                                  appropriate one for the given name.
     */
    public IfcSIUnit(@NonNull IfcUnitEnum unitType,
                     IfcSIPrefix prefix,
                     @NonNull IfcSIUnitName name) {
        super(Functions.ifcDimensionsForSiUnit(name), unitType);
        this.prefix = prefix;
        this.name = name;
    }
}
