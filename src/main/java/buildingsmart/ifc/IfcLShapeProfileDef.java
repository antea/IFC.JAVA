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
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * <p>The <i>IfcLShapeProfileDef</i> defines a section profile that
 * provides the defining parameters of an L-shaped section (equilateral L
 * profiles are also covered by this entity) to be used by the swept area solid.
 * Its parameters and orientation relative to the position coordinate system are
 * according to the following illustration. The shorter leg has the same
 * direction as the positive x-axis, the longer or equal leg the same as the
 * positive y-axis. The centre of the position coordinate system is in the
 * profiles centre of the <strike>gravity</strike> bounding box. </p>
 * <p>The centre of gravity, if given, is located in x direction
 * along the negative x axis, the offset value is given by the offset parameter
 * <i>CentreOfGravityInX</i>, and in y-direction along the negative y axis, the
 * offset value is given by the offset parameter <i>CentreOfGravityInY.</i>
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcLShapeProfileDef extends IfcParameterizedProfileDef {
    @Attribute(3)
    private final IfcPositiveLengthMeasure depth;
    @Attribute(4)
    private final IfcPositiveLengthMeasure width;
    @Attribute(5)
    private final IfcPositiveLengthMeasure thickness;
    @Attribute(6)
    private final IfcPositiveLengthMeasure filletRadius;
    @Attribute(7)
    private final IfcPositiveLengthMeasure edgeRadius;
    @Attribute(8)
    private final IfcPlaneAngleMeasure legSlope;
    @Attribute(9)
    private final IfcPositiveLengthMeasure centreOfGravityInX;
    @Attribute(10)
    private final IfcPositiveLengthMeasure centreOfGravityInY;

    /**
     * @param profileType        Defines the type of geometry into which this
     *                           profile definition shall be resolved, either a
     *                           curve or a surface area. In case of curve the
     *                           profile should be referenced by a swept
     *                           surface, in case of area the profile should be
     *                           referenced by a swept area solid.
     * @param profileName        Name of the profile type according to some
     *                           standard profile table.
     * @param position           Position coordinate system of the parameterized
     *                           profile definition.
     * @param depth              Length of the leg parallel to the y axis.
     * @param width              Length of the leg parallel to the x axis. If
     *                           not given, the value of the depth attribute is
     *                           applied to width.
     * @param thickness          Constant wall thickness of profile.
     * @param filletRadius       Fillet radius. If it is not given, zero is
     *                           assumed.
     * @param edgeRadius         Edge radius. If it is not given, zero is
     *                           assumed.
     * @param legSlope           Slope of leg of the profile. If it is not
     *                           given, zero is assumed.
     * @param centreOfGravityInX Location of centre of gravity along the x axis
     *                           measured from the center of the bounding box.
     * @param centreOfGravityInY Location of centre of gravity along the Y axis
     *                           measured from the center of the bounding box.
     * @throws NullPointerException     If profileType, position, depth or
     *                                  thickness are null.
     * @throws IllegalArgumentException If thickness is not smaller than depth,
     *                                  if width is not null and thickness is
     *                                  not smaller than width.
     */
    @Builder
    public IfcLShapeProfileDef(@NonNull IfcProfileTypeEnum profileType,
                               IfcLabel profileName,
                               @NonNull IfcAxis2Placement2D position,
                               @NonNull IfcPositiveLengthMeasure depth,
                               IfcPositiveLengthMeasure width,
                               @NonNull IfcPositiveLengthMeasure thickness,
                               IfcPositiveLengthMeasure filletRadius,
                               IfcPositiveLengthMeasure edgeRadius,
                               IfcPlaneAngleMeasure legSlope,
                               IfcPositiveLengthMeasure centreOfGravityInX,
                               IfcPositiveLengthMeasure centreOfGravityInY) {
        super(profileType, profileName, position);
        if (thickness.getValue() >= depth.getValue()) {
            throw new IllegalArgumentException(
                    "thickness must be smaller than depth");
        }
        if (width != null && thickness.getValue() >= width.getValue()) {
            throw new IllegalArgumentException(
                    "if width is not null, it must be bigger than thickness");
        }
        this.depth = depth;
        this.width = width;
        this.thickness = thickness;
        this.filletRadius = filletRadius;
        this.edgeRadius = edgeRadius;
        this.legSlope = legSlope;
        this.centreOfGravityInX = centreOfGravityInX;
        this.centreOfGravityInY = centreOfGravityInY;
    }
}
