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
 * <p>The <i>IfcRectangleProfileDef</i> defines a rectangle as the
 * profile definition used by the swept surface geometry or the swept area
 * solid. It is given by its X extent and its Y extent, and placed within the 2D
 * position coordinate system, established by the <i>Position</i> attribute. It
 * is placed <font color="#ff0000">centric</font> within the position coordinate
 * system. </p>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcRectangleProfileDef extends IfcParameterizedProfileDef {
    @Attribute(3)
    private final IfcPositiveLengthMeasure xDim;
    @Attribute(4)
    private final IfcPositiveLengthMeasure yDim;

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
     * @param xDim        The extent of the rectangle in the direction of the
     *                    x-axis.
     * @param yDim        The extent of the rectangle in the direction of the
     *                    y-axis.
     * @throws NullPointerException If profileType, position, xDim or yDim are
     *                              null.
     */
    public IfcRectangleProfileDef(@NonNull IfcProfileTypeEnum profileType,
                                  IfcLabel profileName,
                                  @NonNull IfcAxis2Placement2D position,
                                  @NonNull IfcPositiveLengthMeasure xDim,
                                  @NonNull IfcPositiveLengthMeasure yDim) {
        super(profileType, profileName, position);
        this.xDim = xDim;
        this.yDim = yDim;
    }
}
