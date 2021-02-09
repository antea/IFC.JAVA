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
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * An indication of date and time by measuring the number of seconds which have
 * elapsed since the beginning of the year 1970.
 */
@EqualsAndHashCode
@ToString
public class IfcTimeStamp implements DefinedType, IfcDerivedMeasureValue {
    private final long value;

    /**
     * @param value An indication of date and time by measuring the number of
     *              seconds which have elapsed since the beginning of the year
     *              1970.
     */
    public IfcTimeStamp(long value) {
        this.value = value;
    }

    /**
     * Created a new IfcTimeStamp using the time elapsed since the beginning of
     * the year 1970.
     */
    public IfcTimeStamp() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        this.value = calendar.getTimeInMillis() / 1000; // seconds
    }

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        return Long.toString(value);
    }
}
