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

import lombok.NonNull;

public class IfcAsymmetricIShapeProfileDef extends IfcIShapeProfileDef {
    private final IfcLengthMeasure TopFlangeWidth;
    private final IfcLengthMeasure TopFlangeThickness;
    private final IfcLengthMeasure TopFlangeFilletRadius;
    private final IfcLengthMeasure CentreOfGravityInY;

    public IfcAsymmetricIShapeProfileDef(@NonNull IfcProfileTypeEnum profileType,
                                         IfcLabel profileName,
                                         @NonNull IfcAxis2Placement2D position,
                                         @NonNull IfcPositiveLengthMeasure overallWidth,
                                         @NonNull IfcPositiveLengthMeasure overallDepth,
                                         @NonNull IfcPositiveLengthMeasure webThickness,
                                         @NonNull IfcPositiveLengthMeasure flangeThickness,
                                         IfcPositiveLengthMeasure filletRadius,
                                         IfcLengthMeasure topFlangeWidth,
                                         IfcLengthMeasure topFlangeThickness,
                                         IfcLengthMeasure topFlangeFilletRadius,
                                         IfcLengthMeasure centreOfGravityInY) {
        super(profileType,
              profileName,
              position,
              overallWidth,
              overallDepth,
              webThickness,
              flangeThickness,
              filletRadius);
        TopFlangeWidth = topFlangeWidth;
        TopFlangeThickness = topFlangeThickness;
        TopFlangeFilletRadius = topFlangeFilletRadius;
        CentreOfGravityInY = centreOfGravityInY;
    }
}
