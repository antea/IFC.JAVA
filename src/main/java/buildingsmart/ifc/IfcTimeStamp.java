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
 * An indication of date and time by measuring the number of seconds which have
 * elapsed since the beginning of the year 1970.
 */
public class IfcTimeStamp implements IfcDefinedType, IfcDerivedMeasureValue {
    private final long ifcTimeStamp;

    /**
     * @param ifcTimeStamp An indication of date and time by measuring the
     *                     number of seconds which have elapsed since the
     *                     beginning of the year 1970.
     */
    public IfcTimeStamp(long ifcTimeStamp) {
        this.ifcTimeStamp = ifcTimeStamp;
    }

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return Long.toString(ifcTimeStamp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcTimeStamp that = (IfcTimeStamp) o;
        return ifcTimeStamp == that.ifcTimeStamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ifcTimeStamp);
    }
}
