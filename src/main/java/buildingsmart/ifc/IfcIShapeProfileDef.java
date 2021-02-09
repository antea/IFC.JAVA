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
 * The <i>IfcIShapeProfileDef</i> defines a section profile that provides the
 * defining parameters of a symmetrical 'I' section to be used by the swept
 * surface geometry or the swept area solid. The I-shape profile has values for
 * its overall depth, width and its web and flange thickness. Additionally a
 * fillet radius may be given. It represents a I-section that is symmetrical
 * about its major and minor axes; and that has both top and bottom flanges
 * being equal and centred on the web.
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IfcIShapeProfileDef extends IfcParameterizedProfileDef {
    @Attribute(3)
    private final IfcPositiveLengthMeasure overallWidth;
    @Attribute(4)
    private final IfcPositiveLengthMeasure overallDepth;
    @Attribute(5)
    private final IfcPositiveLengthMeasure webThickness;
    @Attribute(6)
    private final IfcPositiveLengthMeasure flangeThickness;
    @Attribute(7)
    private final IfcPositiveLengthMeasure filletRadius;

    /**
     * @param profileType     Defines the type of geometry into which this
     *                        profile definition shall be resolved, either a
     *                        curve or a surface area. In case of curve the
     *                        profile should be referenced by a swept surface,
     *                        in case of area the profile should be referenced
     *                        by a swept area solid.
     * @param profileName     Name of the profile type according to some
     *                        standard profile table.
     * @param position        Position coordinate system of the parameterized
     *                        profile definition.
     * @param overallWidth    Total extent of the width, defined parallel to the
     *                        x axis of the position coordinate system.
     * @param overallDepth    Total extent of the depth, defined parallel to the
     *                        y axis of the position coordinate system.
     * @param webThickness    Thickness of the web of the I-shape. The web is
     *                        centred on the x-axis and the y-axis of the
     *                        position coordinate system.
     * @param flangeThickness Flange thickness of the I-shape. Both, the upper
     *                        and the lower flanges have the same thickness and
     *                        they are centred on the y-axis of the position
     *                        coordinate system.
     * @param filletRadius    The fillet between the web and the flange, if not
     *                        given, zero is assumed.
     * @throws NullPointerException     If any of the parameters are null,
     *                                  except for {@code profileName} and
     *                                  {@code filletRadius}.
     * @throws IllegalArgumentException If any of the following conditions is
     *                                  not met:
     *                                  <bl>
     *                                  <li>the thickness of the flange shall
     *                                  be less than half of the overall
     *                                  depth;</li>
     *                                  <li>the web thickness shall be less
     *                                  then the overall width;</li>
     *                                  <li>the {@code filletRadius}, if given,
     *                                  should be equal or less than
     *                                  (overallWidth - webThickness) / 2, and
     *                                  equal or less than (overallDepth - (2 *
     *                                  flangeThickness)) / 2.</li>
     *                                  </bl>
     */
    @Builder
    public IfcIShapeProfileDef(@NonNull IfcProfileTypeEnum profileType,
                               IfcLabel profileName,
                               @NonNull IfcAxis2Placement2D position,
                               @NonNull IfcPositiveLengthMeasure overallWidth,
                               @NonNull IfcPositiveLengthMeasure overallDepth,
                               @NonNull IfcPositiveLengthMeasure webThickness,
                               @NonNull IfcPositiveLengthMeasure flangeThickness,
                               IfcPositiveLengthMeasure filletRadius) {
        super(profileType, profileName, position);
        if (flangeThickness.getValue() >= overallDepth.getValue() / 2) {
            throw new IllegalArgumentException(
                    "flangeThickness must be less than half of overallDepth");
        }
        if (webThickness.getValue() >= overallWidth.getValue()) {
            throw new IllegalArgumentException(
                    "webThickness must be less than overallWidth");
        }
        if (filletRadius != null && (filletRadius.getValue() >
                (overallWidth.getValue() - webThickness.getValue()) / 2 ||
                filletRadius.getValue() > (overallDepth.getValue() -
                        (2 * flangeThickness.getValue())) / 2)) {
            throw new IllegalArgumentException(
                    "if given, filletRadius must be equal or less than " +
                            "(overallWidth - webThickness) / 2, and equal or " +
                            "less than (overallDepth - (2 * flangeThickness))" +
                            " / 2");
        }
        this.overallWidth = overallWidth;
        this.overallDepth = overallDepth;
        this.webThickness = webThickness;
        this.flangeThickness = flangeThickness;
        this.filletRadius = filletRadius;
    }
}
