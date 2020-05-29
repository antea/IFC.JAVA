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

import buildingsmart.io.IfcDefinedType;
import buildingsmart.util.Functions;
import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * An identifier is an alphanumeric string which allows an individual thing to
 * be identified. It may not provide natural-language meaning.
 */
public class IfcIdentifier implements IfcDefinedType, IfcSimpleValue {
    private final String ifcIdentifier;

    /**
     * @param ifcIdentifier Restricted to max. 255 characters, cannot be null.
     * @throws IllegalArgumentException If ifcIdentifier is null or longer than
     *                                  255 characters.
     */
    public IfcIdentifier(@NotNull String ifcIdentifier) {
        if (ifcIdentifier == null) {
            throw new IllegalArgumentException("ifcIdentifier cannot be null");
        }
        if (ifcIdentifier.length() > 255) {
            throw new IllegalArgumentException(
                    "ifcIdentifier cannot be " + "longer than 255 characters");
        }
        this.ifcIdentifier = ifcIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcIdentifier that = (IfcIdentifier) o;
        return ifcIdentifier.equals(that.ifcIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ifcIdentifier);
    }

    @Override
    public String serialize() {
        return "'" + Functions.formatForStepFile(ifcIdentifier) + "'";
    }
}
