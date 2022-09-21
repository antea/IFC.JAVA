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

import buildingsmart.io.Attribute;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serializable;

/**
 * A placement entity defines the local environment for the definition of a
 * geometry item. It locates the item to be defined and, in the case of the axis
 * placement subtypes, gives its orientation.
 */
@EqualsAndHashCode(callSuper = false)
@ToString
public abstract class IfcPlacement extends IfcGeometricRepresentationItem implements Serializable {
    @Getter
    @Attribute(0)
    private final IfcCartesianPoint location;

    /**
     * @param location The geometric position of a reference point, such as the
     *                 center of a circle, of the item to be located. Cannot be
     *                 null
     * @throws NullPointerException If location is null.
     */
    public IfcPlacement(@NonNull IfcCartesianPoint location) {
        this.location = location;
    }

    /**
     * @param coordinates The first, second, and third coordinate of the
     *                    reference point, such as the center of a circle, of
     *                    the item to be located. If placed in a two or three
     *                    dimensional rectangular Cartesian coordinate system,
     *                    Coordinates[1] is the X coordinate, Coordinates[2] is
     *                    the Y coordinate, and Coordinates[3] is the Z
     *                    coordinate.
     * @throws NullPointerException     If coordinates is null.
     * @throws IllegalArgumentException If the size of coordinates is lower than
     *                                  2 or bigger than 3.
     */
    public IfcPlacement(double @NonNull ... coordinates) {
        this.location = new IfcCartesianPoint(coordinates);
    }

    /**
     * @return The space dimensionality of this class, derived from the
     * dimensionality of the location.
     */
    public IfcDimensionCount getDim() {
        return location.getDim();
    }
}
