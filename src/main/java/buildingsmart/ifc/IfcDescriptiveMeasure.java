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
import buildingsmart.util.Functions;
import lombok.*;

/**
 * A descriptive measure is a human interpretable definition of a quantifiable value.
 */
@EqualsAndHashCode
@ToString
public class IfcDescriptiveMeasure implements DefinedType, IfcMeasureValue, IfcSizeSelect {
    @Getter(AccessLevel.PACKAGE)
    private final String value;

    /**
     * @param value Cannot be null.
     * @throws NullPointerException If value is null.
     */
    public IfcDescriptiveMeasure(@NonNull String value) {
        this.value = Functions.formatForStepFile(value);
    }

    /**
     * @return The representation of the Defined Type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return "'" + value + "'";
    }
}
