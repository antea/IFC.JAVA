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

/**
 * The IfcCircleHollowProfileDef defines a section profile that provides the
 * defining parameters of a circular hollow section (tube) to be used by the
 * swept area solid. The centre of the position coordinate system is in the
 * profile's centre of the bounding box (for symmetric profiles identical with
 * the centre of gravity).
 * <p><u>Position</u>
 * <br>
 * The parameterized profile defines its own position coordinate system. The
 * underlying coordinate system is defined by the swept area solid that uses the
 * profile definition. It is the xy plane of:</p>
 *       <ul>
 *         <li style="font-style: italic;">IfcSweptAreaSolid.Position</li>
 *       </ul>
 * by using offsets of the position location, the parameterized profile
 * can be positioned centric (using x,y offsets = 0.), or at any position
 * relative to the profile. Explicit coordinate offsets are used to define
 * cardinal points (e.g. upper-left bound).<span
 *  style="font-style: italic;"></span>
 *       <p><u>Parameter</u>
 *       <br>
 * The parameterized profile
 * is defined by a set of parameter attributes, see attribute definition
 * below.</p>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcCircleHollowProfileDef extends IfcCircleProfileDef {
    @Attribute(4)
    private final IfcPositiveLengthMeasure wallThickness;

    /**
     * @param profileType   Defines the type of geometry into which this profile
     *                      definition shall be resolved, either a curve or a
     *                      surface area. In case of curve the profile should be
     *                      referenced by a swept surface, in case of area the
     *                      profile should be referenced by a swept area solid.
     * @param profileName   Name of the profile type according to some standard
     *                      profile table.
     * @param position      Position coordinate system of the parameterized
     *                      profile definition.
     * @param radius        The radius of the circle.
     * @param wallThickness Thickness of the material, it is the difference
     *                      between the outer and inner radius.
     * @throws NullPointerException     If profileType, position, radius or
     *                                  wallThickness are null.
     * @throws IllegalArgumentException If wallThickness is not smaller than
     *                                  radius.
     */
    public IfcCircleHollowProfileDef(@NonNull IfcProfileTypeEnum profileType,
                                     IfcLabel profileName,
                                     @NonNull IfcAxis2Placement2D position,
                                     @NonNull IfcPositiveLengthMeasure radius,
                                     @NonNull IfcPositiveLengthMeasure wallThickness) {
        super(profileType, profileName, position, radius);
        if (wallThickness.getValue() >= radius.getValue()) {
            throw new IllegalArgumentException(
                    "wallThickness must be smaller than the radius");
        }
        this.wallThickness = wallThickness;
    }
}
