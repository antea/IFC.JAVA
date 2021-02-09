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
import buildingsmart.util.Functions;
import lombok.*;

/**
 * The direction and location in three dimensional space of a single axis. An
 * axis1_placement is defined in terms of a locating point (inherited from
 * placement supertype) and an axis direction: this is either the direction of
 * axis or defaults to (0.0,0.0,1.0). The actual direction for the axis
 * placement is given by the derived attribute z (Z).
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcAxis1Placement extends IfcPlacement {
    @Attribute(1)
    private final IfcDirection axis;
    @Getter(AccessLevel.PROTECTED)
    private final IfcDirection z;

    /**
     * @param location The geometric position of a reference point, such as the
     *                 center of a circle, of the item to be located. Cannot be
     *                 null
     * @param axis     The direction of the local Z axis. Defaults to
     *                 (0.0,0.0,1.0) if not given.
     * @throws NullPointerException     If location is null.
     * @throws IllegalArgumentException If location or axis is not
     * three-dimensional.
     */
    public IfcAxis1Placement(@NonNull IfcCartesianPoint location,
                             IfcDirection axis) {
        super(location);
        if (location.getDim().getValue() != 3) {
            throw new IllegalArgumentException(
                    "dimension of location must be" + " 3");
        }
        if (axis != null && axis.getDim().getValue() != 3) {
            throw new IllegalArgumentException("dimension of axis must be 3");
        }
        this.axis = axis;
        this.z = axis == null ? new IfcDirection(0, 0, 1) :
                Functions.ifcNormalise(axis);
    }

    /**
     * Creates an IfcAxis1Placement with the local Z axis defaulting to (0, 0,
     * 1), the actual value of axis will be null.
     *
     * @param location The geometric position of a reference point, such as the
     *                 center of a circle, of the item to be located. Cannot be
     *                 null
     * @throws NullPointerException     If location is null.
     * @throws IllegalArgumentException If location is not three-dimensional.
     */
    public IfcAxis1Placement(@NonNull IfcCartesianPoint location) {
        this(location, null);
    }

    /**
     * Creates an IfcAxis1Placement with the local Z axis defaulting to (0, 0,
     * 1), the actual value of axis will be null.
     *
     * @param locationCoordinates The coordinates of the geometric position of a
     *                            reference point, such as the center of a
     *                            circle, of the item to be located. Cannot be
     *                            null
     * @throws NullPointerException     If location is null.
     * @throws IllegalArgumentException If location is not three-dimensional.
     */
    public IfcAxis1Placement(@NonNull double... locationCoordinates) {
        this(new IfcCartesianPoint(locationCoordinates), null);
    }
}
