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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>The <i>IfcArbitraryProfileDefWithVoids</i> defines an arbitrary closed
 * two-dimensional profile with holes defined for the use for the swept area
 * solid or a sectioned spine. It is given by an outer boundary and inner
 * boundaries from with the solid the can be constructed. </p>
 * <p><u>Informal
 * propositions</u>:</p>
 * <ol>
 *   <li>The outer curve and all
 * inner curves shall be closed curves.</li>
 *   <li>The outer curve shall
 * enclose all inner curves.</li>
 *   <li>No inner curve shall
 * intersect with the outer curve or any other
 * inner curve.</li>
 *   <li>No inner curve may enclose
 * another inner curve.</li>
 * </ol>
 * <p><u>Position</u>
 *       <br>
 * The <i>OuterCurve</i>,
 * defined at the supertype <i>IfcArbitraryClosedProfileDef</i>
 * and the inner curves are defined in the same underlying coordinate
 * system. The common underlying
 * coordinate system is defined by the swept area solid
 * that uses the profile definition. It is the xy plane of: </p>
 *       <ul>
 *         <li style="font-style: italic;">IfcSweptAreaSolid.Position</li>
 *       </ul>
 *       <p>or in case of sectioned
 * spines the xy plane of each list
 * member of <span style="font-style: italic;">IfcSectionedSpine
 * .CrossSectionPositions</span></p>
 *       <p><span
 *  style="font-style: italic;"></span><u>Parameter</u>
 *       <br>
 * The <i>OuterCurve</i>
 * attribute defines a two dimensional closed
 * bounded curve, the <i>InnerCurves</i>
 * define a set of two dimensional
 * closed bounded curves.</p>
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcArbitraryProfileDefWithVoids
        extends IfcArbitraryClosedProfileDef {
    @Attribute(3)
    private final Set<IfcCurve> innerCurves;

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
     * @param innerCurves Set of bounded curves, defining the inner boundaries
     *                    of the arbitrary profile.
     * @throws NullPointerException     If {@code profileType}, {@code
     *                                  outerCurve} or {@code innerCurves} are
     *                                  null.
     * @throws IllegalArgumentException If {@code profileType} is not {@code
     *                                  AREA}, if the dimensionality of {@code
     *                                  outerCurve} is not 2, if {@code
     *                                  outerCurve} is of type IfcLine, if
     *                                  {@code outerCurve} is of type
     *                                  IfcOffsetCurve2D, if {@code innerCurves}
     *                                  has size 0, if any curve in {@code
     *                                  innerCurves} has dimensionality
     *                                  different from 2, if any curve in {@code
     *                                  innerCurves} is of type IfcLine.
     */
    public IfcArbitraryProfileDefWithVoids(@NonNull IfcProfileTypeEnum profileType,
                                           IfcLabel profileName,
                                           @NonNull IfcCurve outerCurve,
                                           @NonNull Set<IfcCurve> innerCurves) {
        super(profileType, profileName, outerCurve);
        if (innerCurves.isEmpty()) {
            throw new IllegalArgumentException(
                    "size of innerCurves must be at least 1");
        }
        if (profileType != IfcProfileTypeEnum.AREA) {
            throw new IllegalArgumentException("profileType must be AREA");
        }
        if (innerCurves.stream()
                .anyMatch(curve -> curve.getDim().getValue() != 2)) {
            throw new IllegalArgumentException(
                    "dimensionality of curves in innerCurves must be 2");
        }
        if (innerCurves.stream().anyMatch(curve -> curve instanceof IfcLine)) {
            throw new IllegalArgumentException(
                    "none of the innerCurves can be of type IfcLine");
        }
        this.innerCurves = innerCurves;
    }

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
     * @param innerCurves Set of bounded curves, defining the inner boundaries
     *                    of the arbitrary profile.
     * @throws NullPointerException     If {@code profileType}, {@code
     *                                  outerCurve} or {@code innerCurves} are
     *                                  null.
     * @throws IllegalArgumentException If {@code profileType} is not {@code
     *                                  AREA}, if the dimensionality of {@code
     *                                  outerCurve} is not 2, if {@code
     *                                  outerCurve} is of type IfcLine, if
     *                                  {@code outerCurve} is of type
     *                                  IfcOffsetCurve2D, if {@code innerCurves}
     *                                  has size 0, if any curve in {@code
     *                                  innerCurves} has dimensionality
     *                                  different from 2, if any curve in {@code
     *                                  innerCurves} is of type IfcLine.
     */
    public IfcArbitraryProfileDefWithVoids(@NonNull IfcProfileTypeEnum profileType,
                                           IfcLabel profileName,
                                           @NonNull IfcCurve outerCurve,
                                           @NonNull IfcCurve... innerCurves) {
        this(profileType,
             profileName,
             outerCurve,
             new HashSet<>(Arrays.asList(innerCurves)));
    }
}
