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
 * The <i>IfcEllipseProfileDef</i> defines an ellipse as the profile definition
 * used by the swept surface geometry or the swept area solid. It is given by
 * its semi axis attributes and placed within the 2D position coordinate system,
 * established by the <i>Position</i> attribute. </p>
 * <p><u>Position</u>
 * <br>
 * The parameterized profile defines its own position coordinate system. The
 * underlying coordinate system is defined by the swept surface or swept area
 * solid that uses the profile definition. It is the xy plane of either: </p>
 *       <ul>
 *         <li style="font-style: italic;">IfcSweptSurface.Position</li>
 *         <li style="font-style: italic;">IfcSweptAreaSolid.Position</li>
 *       </ul>
 * or in case of sectioned spines the xy plane of each list member of <span
 *  style="font-style: italic;">IfcSectionedSpine.CrossSectionPositions.</span>
 *       <br>
 *       <br>
 * By using offsets of the position location, the parameterized profile
 * can be positioned centric (using x,y offsets = 0.), or at any position
 * relative to the profile. Explicit coordinate offsets are used to define
 * cardinal points (e.g. upper-left bound).
 *       <p><u>Parameter</u>
 *       <br>
 * The location of the position coordinate system defines the center of
 * the ellipse. The <i>SemiAxis1</i>
 * attribute defines the first radius
 * of the ellipse in the direction of the x axis, the <i>SemiAxis2</i>
 * attribute defines the second radius of the ellipse in the direction of
 * the y axis.</p>
 * <blockquote><font size="-1">NOTE: The semi axes of the ellipse are
 * rectangular to each other by definition.</font></blockquote>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcEllipseProfileDef extends IfcParameterizedProfileDef {
    @Attribute(3)
    private final IfcPositiveLengthMeasure semiAxis1;
    @Attribute(4)
    private final IfcPositiveLengthMeasure semiAxis2;

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
     * @param semiAxis1   The first radius of the ellipse. It is measured along
     *                    the direction of Position.P[0].
     * @param semiAxis2   The second radius of the ellipse. It is measured along
     *                    the direction of Position.P[1].
     * @throws NullPointerException If profileType, position, semiAxis1 or
     *                              semiAxis2 are null.
     */
    public IfcEllipseProfileDef(@NonNull IfcProfileTypeEnum profileType,
                                IfcLabel profileName,
                                @NonNull IfcAxis2Placement2D position,
                                @NonNull IfcPositiveLengthMeasure semiAxis1,
                                @NonNull IfcPositiveLengthMeasure semiAxis2) {
        super(profileType, profileName, position);
        this.semiAxis1 = semiAxis1;
        this.semiAxis2 = semiAxis2;
    }
}
