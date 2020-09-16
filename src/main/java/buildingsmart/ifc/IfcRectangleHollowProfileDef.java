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
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * <p>The <i>IfcRectangleHollowProfileDef</i> defines a section
 * profile that provides the defining parameters of a rectangular (or square)
 * hollow section to be used by the swept surface geometry or the swept area
 * solid. Its parameters and orientation relative to the position coordinate
 * system are according to the following illustration. A square hollow section
 * can be defined by equal values for h and b. The centre of the position
 * coordinate system is in the profiles centre of the bounding box (for
 * symmetric profiles identical with the centre of gravity). Normally, the
 * longer sides are parallel to the y-axis, the shorter sides parallel to the
 * x-axis.</p>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcRectangleHollowProfileDef extends IfcRectangleProfileDef {
    @Attribute(5)
    private final IfcPositiveLengthMeasure wallThickness;
    @Attribute(6)
    private final IfcPositiveLengthMeasure innerFilletRadius;
    @Attribute(7)
    private final IfcPositiveLengthMeasure outerFilletRadius;

    /**
     * @param profileType       Defines the type of geometry into which this
     *                          profile definition shall be resolved, either a
     *                          curve or a surface area. In case of curve the
     *                          profile should be referenced by a swept surface,
     *                          in case of area the profile should be referenced
     *                          by a swept area solid.
     * @param profileName       Name of the profile type according to some
     *                          standard profile table.
     * @param position          Position coordinate system of the parameterized
     *                          profile definition.
     * @param xDim              The extent of the rectangle in the direction of
     *                          the x-axis.
     * @param yDim              The extent of the rectangle in the direction of
     *                          the y-axis.
     * @param wallThickness     Thickness of the material.
     * @param innerFilletRadius Radius of the circular arcs, by which all four
     *                          corners of the inner contour of rectangle are
     *                          equally rounded. If not given, zero (= no
     *                          rounding arcs) applies.
     * @param outerFilletRadius Radius of the circular arcs, by which all four
     *                          corners of the outer contour of rectangle are
     *                          equally rounded. If not given, zero (= no
     *                          rounding arcs) applies.
     * @throws NullPointerException     If profileType, position, xDim, yDim or
     *                                  wallThickness are null.
     * @throws IllegalArgumentException If any of the following conditions is
     *                                  not met:
     *                                  <ul>
     *                                      <li>the wall thickness shall be
     *                                      smaller than half of the X and Y
     *                                      dimension of the rectangle;</li>
     *                                      <li>the outer fillet radius (if
     *                                      given) shall be smaller than or
     *                                      equal to half of the X and Y
     *                                      dimension of the rectangle;</li>
     *                                      <li>the inner fillet radius (if
     *                                      given) shall be smaller than or
     *                                      equal to half of the X and Y
     *                                      dimension of the rectangle minus
     *                                      the wall thickness.</li>
     *                                  </ul>
     */
    @Builder
    public IfcRectangleHollowProfileDef(@NonNull IfcProfileTypeEnum profileType,
                                        IfcLabel profileName,
                                        @NonNull IfcAxis2Placement2D position,
                                        @NonNull IfcPositiveLengthMeasure xDim,
                                        @NonNull IfcPositiveLengthMeasure yDim,
                                        @NonNull IfcPositiveLengthMeasure wallThickness,
                                        IfcPositiveLengthMeasure innerFilletRadius,
                                        IfcPositiveLengthMeasure outerFilletRadius) {
        super(profileType, profileName, position, xDim, yDim);
        if (wallThickness.getValue() >= xDim.getValue() / 2 ||
                wallThickness.getValue() >= yDim.getValue() / 2) {
            throw new IllegalArgumentException(
                    "wallThickness must be smaller than xDim / 2 and yDim / 2");
        }
        if (outerFilletRadius != null &&
                (outerFilletRadius.getValue() > xDim.getValue() / 2 ||
                        outerFilletRadius.getValue() > yDim.getValue() / 2)) {
            throw new IllegalArgumentException(
                    "outerFilletRadius (if given) must be smaller than or " +
                            "equal to xDim / 2 and yDim / 2");
        }
        if (innerFilletRadius != null && (innerFilletRadius.getValue() >
                xDim.getValue() / 2 - wallThickness.getValue() ||
                innerFilletRadius.getValue() >
                        yDim.getValue() / 2 - wallThickness.getValue())) {
            throw new IllegalArgumentException(
                    "innerFilletRadius (if given) must be smaller than or " +
                            "equal to (xDim / 2) - wallThickness and (yDim / " +
                            "2) - wallThickness");
        }
        this.wallThickness = wallThickness;
        this.innerFilletRadius = innerFilletRadius;
        this.outerFilletRadius = outerFilletRadius;
    }
}
