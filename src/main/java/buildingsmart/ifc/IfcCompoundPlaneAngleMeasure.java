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
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A compound measure of plane angle in degrees, minutes, seconds, and
 * optionally millionth-seconds of arc.
 * <BLOCKQUOTE><FONT SIZE="-1">NOTE: IfcCompoundPlaneAngleMeasure is used
 * where angles need to be described to an accuracy as fine as one millionth of
 * a degree and expressed as parts of an arc. It may be used for angular
 * measurement by surveyors or for other angular measurements where precision is
 * required. Another usage is exact or approximate global positioning against a
 * geographic coordinate systems using longitude and latitude.
 * </FONT></BLOCKQUOTE>
 * <P>Type: LIST [3:4] OF INTEGER </P><p><li>The first integer measure is the
 * number of degrees in the range {360; -360}</li><li>The second integer measure
 * is the number of minutes in the range {60; -60}</li><li>The third integer
 * measure is the number of seconds in the range {60; -60}</li><li >The optional
 * fourth integer measure is the number of millionth-seconds in the range {1 000
 * 000; -1 000 000}</li></p>
 */
public class IfcCompoundPlaneAngleMeasure implements IfcDefinedType, IfcDerivedMeasureValue {
    private final List<IfcInteger> value;

    /**
     * @param value The first integer measure is the number of degrees in the
     *              range {360; -360}; The second integer measure is the number
     *              of minutes in the range {60; -60}</li><li>; The third
     *              integer measure is the number of seconds in the range {60;
     *              -60}; The optional fourth integer measure is the number of
     *              millionth-seconds in the range {1 000 000; -1 000 000}.
     * @throws IllegalArgumentException If value is null, or its size is not 3
     *                                  nor 4, if the elements of value are not
     *                                  within the bounds described above, if
     *                                  the elements of value do not have the
     *                                  same sign.
     */
    public IfcCompoundPlaneAngleMeasure(@NotNull List<IfcInteger> value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
        if (value.size() != 3 && value.size() != 4) {
            throw new IllegalArgumentException("size of value must be 3 or 4");
        }
        if (value.get(0).getValue() < -360 || value.get(0).getValue() >= 360) {
            throw new IllegalArgumentException("value[0] is out of bounds");
        }
        if (value.get(1).getValue() < -60 || value.get(1).getValue() >= 60) {
            throw new IllegalArgumentException("value[1] is out of bounds");
        }
        if (value.get(2).getValue() < -60 || value.get(2).getValue() >= 60) {
            throw new IllegalArgumentException("value[2] is out of bounds");
        }
        if (!((value.get(0).getValue() >= 0 && value.get(1).getValue() >= 0 &&
                value.get(2).getValue() >= 0) ||
                (value.get(0).getValue() <= 0 && value.get(1).getValue() <= 0 &&
                        value.get(2).getValue() <= 0))) {
            throw new IllegalArgumentException(
                    "The measure components must have the same sign (positive" +
                            " or negative)");
        }
        this.value = value;
    }

    /**
     * @param value The first integer measure is the number of degrees in the
     *              range {360; -360}; The second integer measure is the number
     *              of minutes in the range {60; -60}</li><li>; The third
     *              integer measure is the number of seconds in the range {60;
     *              -60}; The optional fourth integer measure is the number of
     *              millionth-seconds in the range {1 000 000; -1 000 000}.
     * @throws IllegalArgumentException If value is null, or its size is not 3
     *                                  nor 4, if the elements of value are not
     *                                  within the bounds described above, if
     *                                  the elements of value do not have the
     *                                  same sign.
     */
    public IfcCompoundPlaneAngleMeasure(@NotNull int... value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
        if (value.length != 3 && value.length != 4) {
            throw new IllegalArgumentException("length of value must be 3 or 4");
        }
        ArrayList<IfcInteger> integers = new ArrayList<>(value.length);
        for (int val : value) {
            integers.add(new IfcInteger(val));
        }
        if (integers.get(0).getValue() < -360 ||
                integers.get(0).getValue() >= 360) {
            throw new IllegalArgumentException("value[0] is out of bounds");
        }
        if (integers.get(1).getValue() < -60 || integers.get(1).getValue() >= 60) {
            throw new IllegalArgumentException("value[1] is out of bounds");
        }
        if (integers.get(2).getValue() < -60 || integers.get(2).getValue() >= 60) {
            throw new IllegalArgumentException("value[2] is out of bounds");
        }
        if (!((integers.get(0).getValue() >= 0 &&
                integers.get(1).getValue() >= 0 &&
                integers.get(2).getValue() >= 0) || (integers.get(0).getValue() <= 0 &&
                integers.get(1).getValue() <= 0 &&
                integers.get(2).getValue() <= 0))) {
            throw new IllegalArgumentException(
                    "The measure components must have the same sign (positive" +
                            " or negative)");
        }
        this.value = integers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IfcCompoundPlaneAngleMeasure that = (IfcCompoundPlaneAngleMeasure) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    //TODO: test serialization

    /**
     * @return The representation of the type in an IFC STEP file.
     */
    @Override
    public String serialize() {
        StringBuilder serialization = new StringBuilder("(");
        for (IfcInteger integer : value) {
            serialization.append(integer.serialize()).append(",");
        }
        serialization.deleteCharAt(serialization.length() - 1);
        serialization.append(")");
        return serialization.toString();
    }
}
