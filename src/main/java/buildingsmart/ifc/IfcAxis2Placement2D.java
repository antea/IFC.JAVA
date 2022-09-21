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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * The location and orientation in two dimensional space of two mutually
 * perpendicular axes. An axis2_placement_2d is defined in terms of a point,
 * (inherited from the placement supertype), and an axis. It can be used to
 * locate and originate an object in two dimensional space and to define a
 * placement coordinate system. The class includes a point which forms the
 * origin of the placement coordinate system. A direction vector is required to
 * complete the definition of the placement coordinate system. The reference
 * direction defines the placement X axis direction, the placement Y axis is
 * derived from this.
 */
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
public class IfcAxis2Placement2D extends IfcPlacement
        implements IfcAxis2Placement, Serializable {
    @Attribute(1)
    private final IfcDirection refDirection;
    /**
     * p[0]: The normalized direction of the placement X Axis. This is (1.0,0.0)
     * if RefDirection is omitted. p[1]: The normalized direction of the
     * placement Y Axis. This is a derived attribute and is orthogonal to p[0].
     */
    @Getter
    @EqualsAndHashCode.Include
    private final List<IfcDirection> p;

    /**
     * @param location     The geometric position of a reference point, such as
     *                     the center of a circle, of the item to be located.
     *                     Cannot be null
     * @param refDirection The direction used to determine the direction of the
     *                     local X Axis.
     * @throws NullPointerException     If location is null.
     * @throws IllegalArgumentException If location is not bidimensional, if
     *                                  refDirection is not null and not
     *                                  bidimensional.
     */
    public IfcAxis2Placement2D(@NonNull IfcCartesianPoint location,
                               IfcDirection refDirection) {
        super(location);
        if (refDirection != null && refDirection.getDim().getValue() != 2) {
            throw new IllegalArgumentException(
                    "if refDirection is not null, it must be bidimensional");
        }
        if (location.getDim().getValue() != 2) {
            throw new IllegalArgumentException(
                    "location must be " + "bidimensional");
        }
        this.refDirection = refDirection;
        p = Functions.ifcBuild2Axes(refDirection);
    }

    /**
     * Creates an IfcAxis2Placement2D with RefDirection defaulting to (1, 0)
     * (field refDirection will actually be null).
     *
     * @param location The geometric position of a reference point, such as the
     *                 center of a circle, of the item to be located. Cannot be
     *                 null
     * @throws NullPointerException     If location is null.
     * @throws IllegalArgumentException If location is not bidimensional.
     */
    public IfcAxis2Placement2D(@NonNull IfcCartesianPoint location) {
        this(location, null);
    }

    /**
     * Creates an IfcAxis2Placement2D with RefDirection defaulting to (1, 0)
     * (field refDirection will actually be null).
     *
     * @param locationCoordinates The coordinates of the geometric position of a
     *                            reference point, such as the center of a
     *                            circle, of the item to be located. Cannot be
     *                            null
     * @throws NullPointerException     If location is null.
     * @throws IllegalArgumentException If location is not bidimensional.
     */
    public IfcAxis2Placement2D(double @NonNull ... locationCoordinates) {
        this(new IfcCartesianPoint(locationCoordinates), null);
    }
}
