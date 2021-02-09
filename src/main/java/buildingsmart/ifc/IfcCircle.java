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
import lombok.NonNull;
import lombok.ToString;

/**
 * An IfcCircle is defined by a radius and the location and orientation of the
 * circle. Its center is located in {@code position.location}, and the circle is
 * placed on the plane defined by {@code position.p[0]} and {@code
 * position.p[1]}.
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcCircle extends IfcConic {
    @Attribute(1)
    private final IfcPositiveLengthMeasure radius;

    /**
     * @param position The location and orientation of the conic. Further
     *                 details of the interpretation of this attribute are given
     *                 for the individual subtypes.
     * @param radius   The radius of the circle, which shall be greater than
     *                 zero.
     * @throws NullPointerException If any of the arguments are null.
     */
    public IfcCircle(@NonNull IfcAxis2Placement position,
                     @NonNull IfcPositiveLengthMeasure radius) {
        super(position);
        this.radius = radius;
    }

    /**
     * @return The space dimensionality of this IfcCurve.
     */
    @Override
    public IfcDimensionCount getDim() {
        return getPosition().getDim();
    }
}
