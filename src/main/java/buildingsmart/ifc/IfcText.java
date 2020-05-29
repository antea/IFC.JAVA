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
 * A text is an alphanumeric string of characters which is intended to be read
 * and understood by a human being. It is for information purposes only.
 */
public class IfcText implements IfcDefinedType, IfcSimpleValue {
    private final String ifcText;

    /**
     * @param ifcText Cannot be null.
     * @throws IllegalArgumentException if ifcText is null.
     */
    public IfcText(@NotNull final String ifcText) {
        if (ifcText == null) {
            throw new IllegalArgumentException("ifcText cannot be null");
        }
        this.ifcText = ifcText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcText ifcText1 = (IfcText) o;
        return ifcText.equals(ifcText1.ifcText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ifcText);
    }

    @Override
    public String serialize() {
        return "'" + Functions.formatForStepFile(ifcText) + "'";
    }
}
