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
 * The closed profile <i>IfcArbitraryClosedProfileDef</i> defines an arbitrary
 * two-dimensional profile for the use within the swept surface geometry, the
 * swept area solid or a sectioned spine. It is given by an outer boundary from
 * which the surface or solid can be constructed. </p>
 * <p><u>Informal
 * proposition</u>: </p>
 * <ol>
 *   <li>The <i>OuterCurve</i>
 * has to be a closed curve.</li>
 *   <li>The <i>OuterCurve</i>
 * shall not intersect.</li>
 * </ol>
 * <p><u>Position</u>
 *       <br>
 * The <i>OuterCurve</i>
 * is defined in the underlying coordinate system. The underlying
 * coordinate system is defined by the swept surface or swept area solid
 * that uses the profile definition. It is the xy plane of either:</p>
 *       <ul>
 *         <li style="font-style: italic;">IfcSweptSurface.Position</li>
 *         <li style="font-style: italic;">IfcSweptAreaSolid.Position</li>
 *       </ul>
 * or in case of sectioned spines the xy plane of each list member of <span
 *  style="font-style: italic;">IfcSectionedSpine.CrossSectionPositions</span>
 *       <p><u>Parameter</u>
 *       <br>
 * The <i>OuterCurve</i>
 * attribute defines a two dimensional closed bounded curve.</p>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcArbitraryClosedProfileDef extends IfcProfileDef {
    @Attribute(2)
    private final IfcCurve outerCurve;

    /**
     * @param profileType Defines the type of geometry into which this profile
     *                    definition shall be resolved, either a curve or a
     *                    surface area. In case of curve the profile should be
     *                    referenced by a swept surface, in case of area the
     *                    profile should be referenced by a swept area solid.
     * @param profileName Name of the profile type according to some standard
     *                    profile table.
     * @param outerCurve  Bounded curve, defining the outer boundaries of the
     *                    arbitrary profile.
     * @throws NullPointerException     If {@code profileType} or {@code
     *                                  outerCurve} are null.
     * @throws IllegalArgumentException If the dimensionality of {@code
     *                                  outerCurve} is not 2, if {@code
     *                                  outerCurve} is of type IfcLine, if
     *                                  {@code outerCurve} is of type
     *                                  IfcOffsetCurve2D.
     */
    public IfcArbitraryClosedProfileDef(@NonNull IfcProfileTypeEnum profileType,
                                        IfcLabel profileName,
                                        @NonNull IfcCurve outerCurve) {
        super(profileType, profileName);
        if (outerCurve.getDim().getValue() != 2) {
            throw new IllegalArgumentException(
                    "dimensionality of outerCurve must be 2");
        }
        if (outerCurve instanceof IfcLine) {
            throw new IllegalArgumentException(
                    "outerCurve cannot be of type IfcLine");
        }
        if (outerCurve instanceof IfcOffsetCurve2D) {
            throw new IllegalArgumentException(
                    "outerCurve cannot be of type IfcOffsetCurve2D");
        }
        this.outerCurve = outerCurve;
    }
}
