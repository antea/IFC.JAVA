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
import buildingsmart.io.Order;
import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * The IfcCircleProfileDef defines a circle as the profile definition used by
 * the swept surface geometry or by the swept area solid. It is given by its
 * Radius attribute and placed within the 2D position coordinate system,
 * established by the Position attribute.
 */
public class IfcCircleProfileDef extends IfcParameterizedProfileDef {
    @Attribute
    @Order(value = 3)
    private final IfcPositiveLengthMeasure radius;

    /**
     * @param profileType Defines the type of geometry into which this profile
     *                    definition shall be resolved, either a curve or a
     *                    surface area. In case of curve the profile should be
     *                    referenced by a swept surface, in case of area the
     *                    profile should be referenced by a swept area solid.
     * @param profileName Name of the profile type according to some standard
     *                    profile table.
     * @param position    Position coordinate system of the parameterized
     *                    profile definition.
     * @param radius      The radius of the circle.
     * @throws IllegalArgumentException If profileType,position or radius are
     *                                  null.
     */
    public IfcCircleProfileDef(@NotNull IfcProfileTypeEnum profileType,
                               IfcLabel profileName,
                               @NotNull IfcAxis2Placement2D position,
                               @NotNull IfcPositiveLengthMeasure radius) {
        super(profileType, profileName, position);
        if (radius == null) {
            throw new IllegalArgumentException("radius cannot be null");
        }
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        IfcCircleProfileDef that = (IfcCircleProfileDef) o;
        return radius.equals(that.radius);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), radius);
    }
}
