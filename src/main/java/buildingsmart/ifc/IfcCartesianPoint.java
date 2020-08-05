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
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * A point defined by its coordinates in a two or three dimensional rectangular
 * Cartesian coordinate system, or in a two dimensional parameter space. The
 * entity is defined in a two or three dimensional space.
 */
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class IfcCartesianPoint extends IfcPoint {
    @Attribute(0)
    private final List<IfcLengthMeasure> coordinates;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private final IfcDimensionCount dim; // derived attribute

    /**
     * @param coordinates The first, second, and third coordinate of the point
     *                    location. If placed in a two or three dimensional
     *                    rectangular Cartesian coordinate system,
     *                    Coordinates[1] is the X coordinate, Coordinates[2] is
     *                    the Y coordinate, and Coordinates[3] is the Z
     *                    coordinate.
     * @throws NullPointerException     If coordinates is null.
     * @throws IllegalArgumentException If the size of coordinates is lower than
     *                                  2 or bigger than 3.
     */
    public IfcCartesianPoint(@NonNull List<IfcLengthMeasure> coordinates) {
        if (coordinates.size() < 2 || coordinates.size() > 3) {
            throw new IllegalArgumentException(
                    "size of coordinates must be 2 or 3");
        }
        this.coordinates = coordinates;
        this.dim = new IfcDimensionCount((byte) coordinates.size());
    }

    /**
     * @param coordinates The first, second, and third coordinate of the point
     *                    location. If placed in a two or three dimensional
     *                    rectangular Cartesian coordinate system,
     *                    Coordinates[1] is the X coordinate, Coordinates[2] is
     *                    the Y coordinate, and Coordinates[3] is the Z
     *                    coordinate.
     * @throws NullPointerException     If coordinates is null.
     * @throws IllegalArgumentException If the size of coordinates is lower than
     *                                  2 or bigger than 3.
     */
    public IfcCartesianPoint(@NonNull double... coordinates) {
        if (coordinates.length < 2 || coordinates.length > 3) {
            throw new IllegalArgumentException(
                    "size of coordinates must be 2 or 3");
        }
        List<IfcLengthMeasure> coordinatesList =
                new ArrayList<>(coordinates.length);
        for (double coordinate : coordinates) {
            coordinatesList.add(new IfcLengthMeasure(coordinate));
        }
        this.coordinates = coordinatesList;
        this.dim = new IfcDimensionCount((byte) coordinatesList.size());
    }

    /**
     * @return The space dimensionality of this class, determined by the number
     * of coordinates in the List of Coordinates.
     */
    public IfcDimensionCount getDim() {
        return dim;
    }
}
