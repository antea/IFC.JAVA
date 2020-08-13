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
 * The <i>IfcTrapeziumProfileDef</i> defines a trapezium as the profile
 * definition used by the swept surface geometry or the swept area solid. It is
 * given by its Top X and Bottom X extent and its Y extent as well as by the
 * offset of the Top X extend, and placed within the 2D position coordinate
 * system, established by the
 * <i>Position</i>
 * attribute. It is placed <font color="#ff0000">centric</font> within the
 * position coordinate system, i.e. in the center of the bounding box. </p>
 * <table frame="border" width="100%">
 *   <tbody>
 *     <tr>
 *       <td align="left" valign="top"
 *  width="100%"><u>Position</u>
 *       <br>
 * The parameterized profile defines its own position coordinate system.
 * The underlying
 * coordinate system is defined by the swept surface or swept area solid
 * that uses the profile definition. It is the xy plane of either:
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
 * The <i>IfcTrapeziumProfileDef</i>
 * is defined within the position
 * coordinate system, where the <i>BottomDim</i>
 * defines the length
 * measure for the bottom line (half along the positive x-axis) and the
 * <i>YDim</i>
 * defines the length measure for the parallel distance of bottom and top
 * line (half along the positive y-axis). The top line starts with a
 * distance of <i>TopXOffset</i>
 * from [-BottomLine/2,YDim] (which can be
 * negative, zero, or positive) and has a length of <i>TopXDim</i>
 * along
 * the positive x-axis.</p>
 *       </td>
 *     </tr>
 *   </tbody>
 * </table>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcTrapeziumProfileDef extends IfcParameterizedProfileDef {
    @Attribute(3)
    private final IfcPositiveLengthMeasure bottomXDim;
    @Attribute(4)
    private final IfcPositiveLengthMeasure topXDim;
    @Attribute(5)
    private final IfcPositiveLengthMeasure yDim;
    @Attribute(6)
    private final IfcLengthMeasure topXOffset;

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
     * @param bottomXDim  The extent of the bottom line measured along the
     *                    implicit x-axis.
     * @param topXDim     The extent of the top line measured along the implicit
     *                    x-axis.
     * @param yDim        The extent of the distance between the parallel bottom
     *                    and top lines measured along the implicit y-axis.
     * @param topXOffset  Offset from the beginning of the top line to the
     *                    bottom line, measured along the implicit x-axis.
     * @throws NullPointerException If profileType, position, bottomXDim,
     *                              topXDim, yDim or topXOffset is null.
     */
    public IfcTrapeziumProfileDef(@NonNull IfcProfileTypeEnum profileType,
                                  IfcLabel profileName,
                                  @NonNull IfcAxis2Placement2D position,
                                  @NonNull IfcPositiveLengthMeasure bottomXDim,
                                  @NonNull IfcPositiveLengthMeasure topXDim,
                                  @NonNull IfcPositiveLengthMeasure yDim,
                                  @NonNull IfcLengthMeasure topXOffset) {
        super(profileType, profileName, position);
        this.bottomXDim = bottomXDim;
        this.topXDim = topXDim;
        this.yDim = yDim;
        this.topXOffset = topXOffset;
    }
}
