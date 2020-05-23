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

import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * A label is the term by which something may be referred to. It is a string
 * which represents the human-interpretable name of something and shall have a
 * natural-language meaning.
 */
public class IfcLabel implements IfcDefinedType, IfcSimpleValue {
    private final String value;

    /**
     * @param value Restricted to max. 255 characters, cannot be null.
     * @throws IllegalArgumentException If value is null or longer than 255
     *                                  characters.
     */
    public IfcLabel(@NotNull String value) {
        if (value == null) {
            throw new IllegalArgumentException("ifcLabel cannot be null");
        }
        if (value.length() > 255) {
            throw new IllegalArgumentException(
                    "ifcLabel cannot be longer " + "than 255 characters");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcLabel that = (IfcLabel) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String serialize() {
        return "'" + value + "'";
    }
}
