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
import buildingsmart.util.Functions;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * An identifier is an alphanumeric string which allows an individual thing to
 * be identified. It may not provide natural-language meaning.
 */
@EqualsAndHashCode
@ToString
public class IfcIdentifier implements DefinedType, IfcSimpleValue {
    private final String value;

    /**
     * @param value Restricted to max. 255 characters, cannot be null.
     * @throws NullPointerException     If value is null.
     * @throws IllegalArgumentException If value is longer than 255 characters
     *                                  after being formatted.
     * @see Functions#formatForStepFile(String)
     */
    public IfcIdentifier(@NonNull String value) {
        value = Functions.formatForStepFile(value);
        if (value.length() > 255) {
            throw new IllegalArgumentException(
                    "ifcIdentifier cannot be " + "longer than 255 characters");
        }
        this.value = value;
    }

    @Override
    public String serialize() {
        return "'" + value + "'";
    }
}
